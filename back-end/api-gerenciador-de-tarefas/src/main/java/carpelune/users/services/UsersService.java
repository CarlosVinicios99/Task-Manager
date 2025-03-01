package carpelune.users.services;

import java.lang.reflect.Field;
import java.util.UUID;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import carpelune.authentication.models.Login;
import carpelune.authentication.repositories.LoginRepository;
import carpelune.exceptions.EmailConflictException;
import carpelune.exceptions.PasswordMismatchException;
import carpelune.users.dto.CreateUserDTO;
import carpelune.users.dto.FindUserDTO;
import carpelune.users.dto.UpdateUserDTO;
import carpelune.users.models.User;
import carpelune.users.models.UserWorkspaceView;
import carpelune.users.repositories.UsersRepository;
import carpelune.users.repositories.UsersWorkspacesViewRepository;
import carpelune.utils.TextEncryptor;

@Service
public class UsersService {
	
	private Logger logger = Logger.getLogger(UsersService.class.getName());
	
	@Autowired
	private UsersRepository usersRepository;
	
	@Autowired
	private LoginRepository loginRepository;
	
	@Autowired
	private UsersWorkspacesViewRepository usersWorkspacesViewRepository;
	
	
	public ResponseEntity<FindUserDTO> createUser(CreateUserDTO createUserDTO){
		
		this.logger.log(Level.INFO, "creating a new user");
		
		this.logger.log(Level.INFO, "checking if the password and confirmation password are the same");
	
		if(!createUserDTO.password().equals(createUserDTO.confirmPassword())) {
			this.logger.log(Level.WARNING, "the confirmation password is incorrect");
			throw new PasswordMismatchException();
		}
		
		this.logger.log(Level.INFO, "checking if email already exists");
		Login emailExists = this.loginRepository.findByEmail(createUserDTO.email());
		
		if(emailExists != null) {
			throw new EmailConflictException();
		}
		
		Login newLogin = new Login();
		newLogin.setEmail(createUserDTO.email());
			
		this.logger.log(Level.INFO, "encrypting the password");
		String encryptedPassword = TextEncryptor.encode(createUserDTO.password());
		newLogin.setPassword(encryptedPassword);
			
		this.logger.log(Level.WARNING, "saving login record in the database");
		newLogin = this.loginRepository.save(newLogin);
			
		User newUser = new User();
		newUser.setName(createUserDTO.name());
		newUser.setLogin(newLogin);
			
		this.logger.log(Level.WARNING, "saving user record in the database");
		User createdUser = this.usersRepository.save(newUser);
			
		return ResponseEntity.status(HttpStatus.CREATED)
			.body(new FindUserDTO(createdUser.getId(), createdUser.getName(), createdUser.getLogin().getEmail()));
			
	}
	
	public ResponseEntity<FindUserDTO> findUserById(UUID userId){
		
		this.logger.log(Level.INFO, "fetching user by ID: " + userId);
		
		try {
			this.logger.log(Level.WARNING, "fetching user from the database");
			User searchedUser = this.usersRepository.findById(userId).get();
			
			if(searchedUser == null) {
				this.logger.log(Level.WARNING, "the provided ID does not correspond to any existing user");
				return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
			}
			
			return ResponseEntity.status(HttpStatus.OK)
				.body(new FindUserDTO(searchedUser.getId(), searchedUser.getName(), searchedUser.getLogin().getEmail()));
			
		}
		catch(Exception error) {
			this.logger.log(Level.SEVERE, "error while fetching user by ID " + error.getMessage());
			return ResponseEntity.status(HttpStatus.SERVICE_UNAVAILABLE).build();
		}
	}
	
	public ResponseEntity<Page<UserWorkspaceView>> findUsersByWorkspace(UUID workspaceId, int page, int limit){
		
		this.logger.log(Level.INFO, "fetching users by workspace");
		
		try {
			Pageable pageable = PageRequest.of(page, limit);
			Page<UserWorkspaceView> usersWorkspaces = this.usersWorkspacesViewRepository.findByWorkspaceId(workspaceId, pageable);
			return ResponseEntity.status(HttpStatus.OK).body(usersWorkspaces);
		}
		catch(Exception error) {
			this.logger.log(Level.SEVERE, "error while fetching users by workspace " + error.getMessage());
			return ResponseEntity.status(HttpStatus.SERVICE_UNAVAILABLE).build();
		}
	}
	
	public ResponseEntity<Void> updateUser(UpdateUserDTO updateUserDTO){
		
		this.logger.log(Level.INFO, "updating user");
		
		try {
			this.logger.log(Level.WARNING, "fetching user from the database");
			User updatedUser = this.usersRepository.findById(updateUserDTO.id()).get();
			
			if(updatedUser == null) {
				this.logger.log(Level.WARNING, "the provided ID does not correspond to any existing user");
				return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
			}
			
			Field[] fields = UpdateUserDTO.class.getDeclaredFields();
			
			for(Field field : fields) {
	            field.setAccessible(true);
	            Object value = field.get(updateUserDTO);

	            if (value != null) {
	                try {
	                    Field userField = User.class.getDeclaredField(field.getName());
	                    userField.setAccessible(true);
	                    userField.set(updatedUser, value);
	                } 
	                catch(NoSuchFieldException e) {
	                    logger.log(Level.WARNING, "Field " + field.getName() + "field not found in user");
	                }
	            }
	        }
			
			this.logger.log(Level.WARNING, "updating user in the database");
			this.usersRepository.save(updatedUser);
			
			return ResponseEntity.status(HttpStatus.OK).build();
			
		}
		catch(Exception error) {
			this.logger.log(Level.SEVERE, "error while updating user " + error.getMessage());
			return ResponseEntity.status(HttpStatus.SERVICE_UNAVAILABLE).build();
		}
	}
	
	public ResponseEntity<Void> deleteUserById(UUID userId) { 
		
		this.logger.log(Level.INFO, "Deleting user with ID: " + userId);
		
		try {
			this.logger.log(Level.WARNING, "deleting user from the database");
			this.usersRepository.deleteById(userId);
			
			return ResponseEntity.status(HttpStatus.OK).build();
		}
		catch(Exception error) {
			this.logger.log(Level.SEVERE, "error while deleting user by ID " + error.getMessage());
			return ResponseEntity.status(HttpStatus.SERVICE_UNAVAILABLE).build();
		}
	}
}
