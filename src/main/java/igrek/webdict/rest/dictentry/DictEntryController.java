package igrek.webdict.rest.dictentry;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import igrek.webdict.db.dictentry.DictEntryDao;
import igrek.webdict.db.dictionary.DictionaryDao;
import igrek.webdict.model.DictEntry;

@Controller
@RequestMapping("/entry")
public class DictEntryController {
	
	private final Logger logger = LoggerFactory.getLogger(this.getClass());
	
	private final DictionaryDao dictionaryDao;
	
	private final DictEntryDao dictEntryDao;
	
	@Autowired
	public DictEntryController(DictionaryDao dictionaryDao, DictEntryDao dictEntryDao) {
		this.dictionaryDao = dictionaryDao;
		this.dictEntryDao = dictEntryDao;
	}
	
	@GetMapping("/{id}")
	public @ResponseBody
	DictEntry getById(@PathVariable("id") long id) {
		return dictEntryDao.findOne(id).orElse(null);
	}
	
	@GetMapping("/new")
	public @ResponseBody
	StringWrapper welcome() {
		return new StringWrapper("DUPA");
	}
	
	private class StringWrapper {
		private String dupa;
		
		public StringWrapper(String dupa) {
			this.dupa = dupa;
		}
		
		public String getDupa() {
			return dupa;
		}
		
		public void setDupa(String dupa) {
			this.dupa = dupa;
		}
	}
}
