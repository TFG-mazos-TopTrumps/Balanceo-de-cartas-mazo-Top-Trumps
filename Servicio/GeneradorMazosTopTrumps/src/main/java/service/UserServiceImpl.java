package service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import dao.UsersDao;
import model.User;

@Service
public class UserServiceImpl implements UserService {

	UsersDao usersDao;
	
	
	public UserServiceImpl(@Autowired UsersDao usersDao) {
		super();
		this.usersDao = usersDao;
	}
	public void registerUser(User u) {
		usersDao.save(u);
		
	}


	public boolean login(String username, String password) {
		
		User u = usersDao.findUserByUsernameAndPassword(username, password);
		
		return (u != null) ? true:false;
	}


	

}
