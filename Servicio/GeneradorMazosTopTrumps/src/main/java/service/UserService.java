package service;



import model.User;

public interface UserService {
	
	void registerUser(User u);
	User login(String username, String password);

}
