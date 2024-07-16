package carpelune.authentication.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Service;

import carpelune.authentication.dto.AuthenticationDTO;
import carpelune.authentication.dto.LoginResponseDTO;
import carpelune.authentication.models.Login;
import carpelune.config.security.TokenService;

@Service
public class AuthenticationService {
	
	@Autowired
	private AuthenticationManager authenticationManager;
	
	@Autowired
	private TokenService tokenService;
	

	public ResponseEntity<LoginResponseDTO> login(AuthenticationDTO data) {
		try {
			var usernamePassword = new UsernamePasswordAuthenticationToken(data.email(), data.password());
			var auth = this.authenticationManager.authenticate(usernamePassword);
			var token = tokenService.generateToken((Login) auth.getPrincipal());
			return ResponseEntity.ok(new LoginResponseDTO(token));
		}
		catch(Exception error) {
			System.out.println(error.getMessage());
			return ResponseEntity.badRequest().build();
		}
	}	
}
