package service;

import java.sql.SQLException;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;

import javax.servlet.http.HttpSession;
import javax.validation.ConstraintViolationException;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;

import dao.UsersDao;
import model.Deck;
import model.User;

@Service
public class UserServiceImpl implements UserService {

	UsersDao usersDao;
	
	
	public UserServiceImpl(@Autowired UsersDao usersDao) {
		super();
		this.usersDao = usersDao;
	}
	public void registerUser(User u) throws SQLException, ConstraintViolationException {
		
		
		boolean errorNotNullUsername = u.getUsername() == null || u.getUsername().isBlank() || u.getUsername().isEmpty() ? true:false;
		boolean errorDuplicatedUsername = this.usersDao.countUserByUsername(u.getUsername()) == 1;
		boolean errorNotNullPassword = u.getPassword() == null || u.getPassword().isBlank() || u.getPassword().isEmpty() ? true:false;
		boolean errorNotNullName = u.getName() == null || u.getName().isBlank() || u.getName().isEmpty() ? true:false;
		boolean errorMaxLengthUsername = u.getUsername().length() >= 45 ? true:false;
		boolean errorMaxLengthPassword = u.getPassword().length() >= 45 ? true:false;
		boolean errorMaxLengthName = u.getName().length() >= 50 ? true:false;
		boolean anyError=false;
		
		try {
			
			if(errorDuplicatedUsername) {
				anyError=true;
				throw new SQLException("El nombre de usuario indicado ya se encuentra registrado en el sistema.");
				
			}
			
			if(!anyError && errorNotNullUsername) {
				anyError=true;
				throw new ConstraintViolationException("El campo nombre del usuario no puede ser nulo.",null);
				
			} 
			
			if(!anyError && errorNotNullPassword) {
				anyError=true;
				throw new ConstraintViolationException("El campo contraseña no puede ser nulo.",null);
				
			}
			
			if(!anyError && errorNotNullName) {
				anyError=true;
				throw new ConstraintViolationException("El campo nombre no puede ser nulo.",null);
				
			}
			
			if(!anyError && errorMaxLengthUsername) {
				anyError=true;
				throw new ConstraintViolationException("El nombre de usuario no puede estar compuesto de 45 o más caracteres.",null);
				
			}
			
			if(!anyError && errorMaxLengthPassword) {
				anyError=true;
				throw new ConstraintViolationException("La contraseña no puede estar compuesta de 45 o más caracteres.",null);
				
				
			}
			
			if(!anyError && errorMaxLengthName) {
				anyError=true;
				throw new ConstraintViolationException("El nombre no puede estar compuesto de o más 50 caracteres.",null);
				
				
			}
			if(!anyError) {
				usersDao.save(u);
			}
		} catch(SQLException e) {
			System.out.println("El nombre de usuario indicado ya se encuentra registrado en el sistema.");
			
		} catch(ConstraintViolationException e) {
			
			if(errorNotNullUsername) {
				System.out.println("El campo nombre del usuario no puede ser nulo.");
				
			}
			
			if(errorNotNullPassword) {
				System.out.println("El campo contraseña no puede ser nulo.");
				
			}
			
			if(errorNotNullName) {
				System.out.println("El campo nombre no puede ser nulo.");
				
			}
			
			if(errorMaxLengthUsername) {
				System.out.println("El nombre de usuario no puede estar compuesto de 45 o más caracteres.");
	
			}
			
			if(errorMaxLengthPassword) {
				System.out.println("La contraseña no puede estar compuesta de 45 o más caracteres.");
	
			}
			
			if(errorMaxLengthName) {
				System.out.println("El nombre no puede estar compuesto de 50 o más caracteres.");
	
			}
				
			}
		
	}


	public User login(String username, String password) {

			User u = usersDao.findUserByUsernameAndPassword(username, password);
			return u;
	
	}
	
	public User findUserByUsername(String username) {
		
		return usersDao.findUserByUsername(username);
	}
	@Override
	public Integer countUserByUsername(String username) {
		
		return usersDao.countUserByUsername(username);
	}
	
	
	



	

}
