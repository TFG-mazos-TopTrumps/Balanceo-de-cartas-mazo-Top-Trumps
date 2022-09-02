package service;



import java.sql.SQLException;

import javax.validation.ConstraintViolationException;

import model.User;

public interface UserService {
	
	User findUserByUsername(String username);
	Integer countUserByUsername(String username);
	void registerUser(User u) throws SQLException, ConstraintViolationException;
	User login(String username, String password);

}
