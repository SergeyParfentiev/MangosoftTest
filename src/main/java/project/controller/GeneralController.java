package project.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import project.dto.UserDTO;
import project.service.UserService;

@RestController
@RequestMapping("/")
public class GeneralController {

    @Autowired private UserService userService;
    @Autowired private BCryptPasswordEncoder bCryptPasswordEncoder;

    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public UserDTO create(@RequestParam String login, @RequestParam String password,
                          @RequestParam String firstName, @RequestParam String lastName){
        UserDTO user = new UserDTO();
        user.setLogin(login);
        user.setPassword(bCryptPasswordEncoder.encode(password));
        user.setFirstName(firstName);
        user.setLastName(lastName);
        return userService.saveUser(user);
    }
}
