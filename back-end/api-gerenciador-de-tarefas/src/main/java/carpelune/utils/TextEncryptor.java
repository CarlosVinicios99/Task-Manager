package carpelune.utils;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

public class TextEncryptor {
	
	private static PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
	
	private TextEncryptor() {}
	
	
	public static String encode(String text) {
		return passwordEncoder.encode(text);
	}
	
}
