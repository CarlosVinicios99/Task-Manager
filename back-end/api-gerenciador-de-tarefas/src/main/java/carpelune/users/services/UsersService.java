package carpelune.users.services;

import java.util.logging.Level;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import carpelune.users.dto.createUserDTO;
import carpelune.users.models.User;
import carpelune.users.repositories.UsersRepository;
import carpelune.utils.TextEncryptor;

@Service
public class UsersService {
	
	private Logger logger = Logger.getLogger(UsersService.class.getName());
	
	@Autowired
	private UsersRepository usersRepository;
	
	
	public ResponseEntity<User> createUser(createUserDTO createUserDTO){
		
		this.logger.log(Level.INFO, "creating a new user");
		
		this.logger.log(Level.INFO, "checking if the password and confirmation password are the same");
		
		if(createUserDTO.password() != createUserDTO.confirmPassword()) {
			this.logger.log(Level.WARNING, "the confirmation password is incorrect");
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
		}
		
		try {
			User newUser = new User();
			newUser.setName(createUserDTO.name());
			newUser.setEmail(createUserDTO.email());
			
			this.logger.log(Level.INFO, "encrypting the password");
			String encryptedPassword = TextEncryptor.encode(createUserDTO.password());
			newUser.setPassword(encryptedPassword);
			
			this.logger.log(Level.WARNING, "saving user record in the database");
			this.usersRepository.save(newUser);
			
			return ResponseEntity.status(HttpStatus.OK).build();
			
		}
		catch(Exception error) {
			this.logger.log(Level.SEVERE, "error while registering user " + error.getMessage());
			return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).build();
		}	
	}
	
	
	
}
