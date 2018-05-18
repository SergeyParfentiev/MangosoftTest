package project.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import project.dto.UserDTO;
import project.service.UserService;

import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {

	@Autowired private UserService userService;

	@RequestMapping(value="/list", method = RequestMethod.GET)
	public List<UserDTO> userList(@RequestParam int pageNumber, @RequestParam int pageCount){
		return userService.getUserListByPageNumberAndCount(pageNumber, pageCount);
	}

	@RequestMapping(value = "/update/{login}", method = RequestMethod.POST)
	public UserDTO update(@PathVariable(value = "login") String login,
						  @RequestParam String firstName, @RequestParam String lastName){
		UserDTO user = userService.getUserByLogin(login);
		user.setFirstName(firstName);
		user.setLastName(lastName);
		return userService.saveUser(user);
	}

	@RequestMapping(value = "/user/{login}", method = RequestMethod.DELETE)
	public String delete(@PathVariable(value = "login") String login){
		userService.deleteUserByLogin(login);
		return "success";
	}
}
