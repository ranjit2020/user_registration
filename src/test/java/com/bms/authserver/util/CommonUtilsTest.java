package com.bms.authserver.util;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class CommonUtilsTest {

	@Test
	void bcryptPasswordEncoderTest() {
		
		assertNotNull(CommonUtils.bcryptPasswordEncoder("Guess@851"));
	}

}
