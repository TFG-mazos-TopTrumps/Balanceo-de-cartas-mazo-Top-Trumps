package service;



import model.User;

public interface UserService {
	
	User findUserById(Integer id);
	void registerUser(User u);
	User login(String username, String password);

}
