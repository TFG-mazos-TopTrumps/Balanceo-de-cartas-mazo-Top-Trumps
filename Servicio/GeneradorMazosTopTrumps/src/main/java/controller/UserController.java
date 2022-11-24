package controller;


import java.sql.SQLException;


import javax.validation.ConstraintViolationException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import model.User;
import service.UserService;

@RestController
@CrossOrigin("*")
public class UserController {
	
	@Autowired
	UserService userService;
	
	
	@GetMapping(value="UserByUsername")
	public User getUserByUsername(@RequestParam("username") String username) throws Exception {
		
		User u = userService.findUserByUsername(username);
		
		return u;
		
	}
	@GetMapping(value="UserId")
	public Integer getIdUser(@RequestParam("username") String username, @RequestParam("password") String password) throws Exception{
		
		User u = userService.login(username, password);
		
		return u.getIdUser();
		
	}
	
	@GetMapping(value="CountUserByUsername")
	public Integer countUserByUsername(@RequestParam("username") String username) {
		
		return this.userService.countUserByUsername(username);
		
	}
	
	@PostMapping(value="Login")
	public boolean login(@RequestParam("username") String username, @RequestParam("password") String password) throws SQLException, ConstraintViolationException, Exception {
		
		User u = userService.login(username, password);
		
		if(u != null) {
			
			return true;
		} else {
			
			return false;
		}
		
	}
	
	@PostMapping(value="Register", produces=MediaType.APPLICATION_JSON_VALUE, consumes=MediaType.APPLICATION_JSON_VALUE)
	public boolean registerUser(@RequestBody User u) throws SQLException, ConstraintViolationException {
		return userService.registerUser(u);
	}
	

}
