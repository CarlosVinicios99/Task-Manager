package carpelune.users.controllers;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import carpelune.users.dto.CreateUserDTO;
import carpelune.users.dto.FindUserDTO;
import carpelune.users.dto.UpdateUserDTO;
import carpelune.users.models.UserWorkspaceView;
import carpelune.users.services.UsersService;
import jakarta.validation.Valid;

@CrossOrigin
@RequestMapping("/users")
@RestController
public class UsersController {
	
	@Autowired
	private UsersService usersService;
	
	
	@PostMapping()
	public ResponseEntity<FindUserDTO> createUser(@RequestBody @Valid CreateUserDTO newUser){
		return this.usersService.createUser(newUser);
	}
	
	@GetMapping("/{userId}")
	public ResponseEntity<FindUserDTO> findUserById(@PathVariable UUID userId){
		return this.usersService.findUserById(userId);
	}
	
	@GetMapping("/{workspaceId}")
	public ResponseEntity<Page<UserWorkspaceView>> findUsersByWorkspace(
		@PathVariable UUID workspaceId,
		@RequestParam(value = "page", defaultValue = "0") int page, 
		@RequestParam(value = "limit", defaultValue = "5") int limit
	){
		return this.usersService.findUsersByWorkspace(workspaceId, page, limit);
	}
	
	@PutMapping()
	public ResponseEntity<Void> updateUser(@RequestBody @Valid UpdateUserDTO updateUser){
		return this.usersService.updateUser(updateUser);
	}
	
	@DeleteMapping("/{userId}")
	public ResponseEntity<Void> deleteUserById(@PathVariable UUID userId){
		return this.deleteUserById(userId);
	}
	
}
