package carpelune.users.services;

import java.util.UUID;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import carpelune.users.dto.CreateUserDTO;
import carpelune.users.dto.FindUserByIdDTO;
import carpelune.users.models.User;
import carpelune.users.repositories.UsersRepository;
import carpelune.utils.TextEncryptor;

@Service
public class UsersService {
	
	private Logger logger = Logger.getLogger(UsersService.class.getName());
	
	@Autowired
	private UsersRepository usersRepository;
	
	
	public ResponseEntity<User> createUser(CreateUserDTO createUserDTO){
		
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
			
			return ResponseEntity.status(HttpStatus.CREATED).build();
			
		}
		catch(Exception error) {
			this.logger.log(Level.SEVERE, "error while registering user " + error.getMessage());
			return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).build();
		}
		
	}
	
	public ResponseEntity<FindUserByIdDTO> findUserById(UUID userId){
		
		this.logger.log(Level.INFO, "fetching user by ID: " + userId);
		
		if(userId == null || userId.toString().isEmpty()) {
			this.logger.log(Level.WARNING, "The provided ID is undefined");
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
		}
		
		try {
			this.logger.log(Level.WARNING, "fetching user from the database");
			User searchedUser = this.usersRepository.findById(userId).get();
			
			if(searchedUser == null) {
				this.logger.log(Level.WARNING, "the provided ID does not correspond to any existing user");
				return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
			}
			
			return ResponseEntity.status(HttpStatus.OK)
				.body(new FindUserByIdDTO(searchedUser.getId(), searchedUser.getName(), searchedUser.getEmail()));
			
		}
		catch(Exception error) {
			this.logger.log(Level.SEVERE, "error while fetching user by ID " + error.getMessage());
			return ResponseEntity.status(HttpStatus.SERVICE_UNAVAILABLE).build();
		}
	}
	
}
