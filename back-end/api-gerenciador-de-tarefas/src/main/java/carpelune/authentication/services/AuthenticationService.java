package carpelune.authentication.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Service;

import carpelune.authentication.dto.AuthenticationDTO;
import carpelune.authentication.dto.LoginResponseDTO;
import carpelune.authentication.models.Login;
import carpelune.authentication.repositories.LoginRepository;
import carpelune.config.security.TokenService;
import carpelune.users.models.User;
import carpelune.users.repositories.UsersRepository;

@Service
public class AuthenticationService {
	
	@Autowired
	private AuthenticationManager authenticationManager;
	
	@Autowired
	private TokenService tokenService;
	
	@Autowired
	private LoginRepository loginRepository;
	
	@Autowired
	private UsersRepository userRepository;
	

	public ResponseEntity<LoginResponseDTO> login(AuthenticationDTO data) {
		try {
			var usernamePassword = new UsernamePasswordAuthenticationToken(data.email(), data.password());
			var auth = this.authenticationManager.authenticate(usernamePassword);
			var token = tokenService.generateToken((Login) auth.getPrincipal());
			Login login = this.loginRepository.findByEmail(data.email());
			User user = this.userRepository.findByLoginId(login.getId());
			return ResponseEntity.ok(new LoginResponseDTO(token, user.getId()));
		}
		catch(Exception error) {
			System.out.println(error.getMessage());
			return ResponseEntity.badRequest().build();
		}
	}	
}
