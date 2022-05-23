package com.bms.authserver.util;

import org.springframework.security.crypto.bcrypt.BCrypt;

public class CommonUtils {

	private CommonUtils() {
		
	}
	public static  String bcryptPasswordEncoder(String password) {
	
		return BCrypt.hashpw(password, BCrypt.gensalt(12));
	}
	
	
	
	}