package service;



import java.sql.SQLException;

import javax.validation.ConstraintViolationException;

import model.User;

public interface UserService {
	
	User findUserByUsername(String username) throws Exception;
	Integer countUserByUsername(String username);
	boolean registerUser(User u) throws SQLException, ConstraintViolationException;
	User login(String username, String password) throws Exception;

}
