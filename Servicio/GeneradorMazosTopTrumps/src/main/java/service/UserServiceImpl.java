package service;

import javax.servlet.http.HttpServletRequest;

import javax.servlet.http.HttpSession;

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


	public User login(String username, String password) {
		
		User u = usersDao.findUserByUsernameAndPassword(username, password);
		
		return u;
	}
	
	public User findUserById(Integer id) {
		
		return usersDao.findById(id).get();
	}


	

}
