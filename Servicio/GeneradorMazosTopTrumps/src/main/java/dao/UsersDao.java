package dao;

import org.springframework.data.jpa.repository.JpaRepository;

import model.User;

public interface UsersDao extends JpaRepository<User, Integer> {
	
	User findUserByUsernameAndPassword(String username, String password);

}
