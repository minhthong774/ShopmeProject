package com.shopme.admin.user;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class PasswordEncoderTest {
	@Test
	public void testEncodePassword() {
		BCryptPasswordEncoder passwordEncoded = new BCryptPasswordEncoder();
		String rawPassword = "thong2021";
		String encodedPassword = passwordEncoded.encode(rawPassword);
		
		System.out.println(encodedPassword);
		
		boolean matches = passwordEncoded.matches(rawPassword, encodedPassword);
		assertThat(matches).isTrue();
	}
}
