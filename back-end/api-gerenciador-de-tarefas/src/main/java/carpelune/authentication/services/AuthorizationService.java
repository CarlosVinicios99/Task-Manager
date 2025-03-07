package carpelune.authentication.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import carpelune.authentication.repositories.LoginRepository;

@Service
public class AuthorizationService implements UserDetailsService{
	
	@Autowired
	private LoginRepository userRepository;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		return userRepository.findByEmail(username);
	}
	
	
}