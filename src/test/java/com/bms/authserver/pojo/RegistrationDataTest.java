package com.bms.authserver.pojo;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class RegistrationDataTest {

	@Test
	void registrationDataTest() {
		RegistrationData data = new RegistrationData();
		data.setAccountType("savings");
		data.setAddress("mumbai");
		data.setContact("9898981890");
		data.setFirstName("ranjit");
		
		assertEquals("savings", data.getAccountType());
		assertEquals("mumbai", data.getAddress());
		assertEquals("9898981890", data.getContact());
		assertEquals("ranjit", data.getFirstName());
	}

}
