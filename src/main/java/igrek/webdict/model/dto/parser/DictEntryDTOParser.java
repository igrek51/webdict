package igrek.webdict.model.dto.parser;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import igrek.webdict.model.DictEntry;
import igrek.webdict.model.dto.DictEntryDTO;

public class DictEntryDTOParser {
	
	private static final DateTimeFormatter LAST_USE_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
	
	public static DictEntry parse(DictEntryDTO dto) {
		LocalDateTime lastUse = dto.getLastUse() == null ? null : LocalDateTime.parse(dto.getLastUse(), LAST_USE_FORMATTER);
		return new DictEntry(dto.getId(), dto.getDictionaryId(), dto.getWord(), dto.getDefinition(), dto
				.getRank(), lastUse);
	}
	
	public static DictEntryDTO parse(DictEntry dictEntry) {
		String lastUse = dictEntry.getLastUse() == null ? null : dictEntry.getLastUse()
				.format(LAST_USE_FORMATTER);
		return new DictEntryDTO(dictEntry.getId(), dictEntry.getDictionaryId(), dictEntry.getWord(), dictEntry
				.getDefinition(), dictEntry.getRank(), lastUse);
	}
}
