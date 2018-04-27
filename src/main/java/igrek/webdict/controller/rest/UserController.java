package igrek.webdict.controller.rest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

import igrek.webdict.domain.user.User;
import igrek.webdict.service.UserService;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@RestController
@RequestMapping("/api/user")
class UserController {
	
	Logger logger = LoggerFactory.getLogger(this.getClass());
	
	private final UserService userService;
	
	@Autowired
	public UserController(UserService userService) {
		this.userService = userService;
	}
	
	@GetMapping({"", "/", "all"})
	public List<UserDTO> getAll() {
		return userService.findAll().stream().map(UserDTO::createDTO).collect(Collectors.toList());
	}
	
	@Data
	@NoArgsConstructor
	@AllArgsConstructor
	public static class UserDTO {
		private Long id;
		private String login;
		
		static UserDTO createDTO(User user) {
			return new UserDTO(user.getId(), user.getLogin());
		}
	}
}
