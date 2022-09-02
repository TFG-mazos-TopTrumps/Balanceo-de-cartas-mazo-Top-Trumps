package dao;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import model.User;

public interface UsersDao extends JpaRepository<User, Integer> {
	
	@Query("select u from User u where u.username=?1 and u.password=?2")
	User findUserByUsernameAndPassword(String username, String password);
	
	@Query("select u from User u where u.username=?1")
	User findUserByUsername(String username);
	
	@Query("select count(*) from User u where u.username=?1")
	Integer countUserByUsername(String username);

	
}
