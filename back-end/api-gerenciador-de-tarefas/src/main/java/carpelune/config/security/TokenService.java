package carpelune.config.security;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;

import carpelune.authentication.models.Login;

@Service
public class TokenService {
	
	@Value("${api.security.token.secret}")
	private String secret;
	
	private Instant generatedExpirationDate() {
		return LocalDateTime.now().plusHours(2).toInstant(ZoneOffset.of("-03:00"));
	}
	
	public String generateToken(Login login) {
		try {
			Algorithm algorithm = Algorithm.HMAC256(secret);
			String token = JWT.create()
					.withIssuer("auth-api")
					.withSubject(login.getEmail())
					.withExpiresAt(generatedExpirationDate())
					.sign(algorithm);
			return token;
		}
		catch(JWTCreationException error) {
			throw new RuntimeException("Error while generation token", error);
		}
	}
	
	public String validateToken(String token) {
		try {
			Algorithm algorithm = Algorithm.HMAC256(secret);
			return JWT.require(algorithm)
				.withIssuer("task-manager-api")
				.build()
				.verify(token)
				.getSubject();
		}
		catch(JWTVerificationException error) {
			return "";
		}
	}
	
}
