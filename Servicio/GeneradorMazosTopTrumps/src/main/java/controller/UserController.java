package controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import model.User;
import service.UserService;

@RestController
@CrossOrigin("*")
public class UserController {
	
	@Autowired
	UserService userService;
	
	@PostMapping(value="Login/{username}/{password}", produces=MediaType.APPLICATION_JSON_VALUE)
	public boolean login(@PathVariable("username") String username, @PathVariable("password") String password) {
		return userService.login(username, password);
	}
	
	@PostMapping(value="Register", produces=MediaType.APPLICATION_JSON_VALUE)
	public void registerUser(@RequestBody User u) {
		userService.registerUser(u);
	}
	

}
