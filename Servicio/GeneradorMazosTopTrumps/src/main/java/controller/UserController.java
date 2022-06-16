package controller;


import javax.servlet.http.HttpSession;

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
	
	@GetMapping(value="UserId")
	public Integer getIdUser(@RequestParam("username") String username, @RequestParam("password") String password) {
		
		User u = userService.login(username, password);
		
		return u.getIdUser();
		
	}
	
	@PostMapping(value="Login")
	public boolean login(@RequestParam("username") String username, @RequestParam("password") String password, HttpSession session) {
		
		User u = userService.login(username, password);
		
		if(u != null) {
			session.setAttribute("usuario", u);
			return true;
		} else {
			session.setAttribute("mensaje", "Usuario incorrecto");
			return false;
		}
		
	}
	
	@PostMapping(value="Register", produces=MediaType.APPLICATION_JSON_VALUE, consumes=MediaType.APPLICATION_JSON_VALUE)
	public void registerUser(@RequestBody User u) {
		userService.registerUser(u);
	}
	

}
