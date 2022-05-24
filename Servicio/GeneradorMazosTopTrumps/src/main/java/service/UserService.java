package service;

import model.User;

public interface UserService {
	
	void registerUser(User u);
	boolean login(String username, String password);

}
