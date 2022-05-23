package com.bms.authserver.pojo;

import static org.junit.Assert.assertEquals;

import org.junit.jupiter.api.Test;

class ResponseDataTest {

	@Test
	void responseDataTest() {
		ResponseData responseData =  new ResponseData();
		responseData.setCode(200);
		responseData.setMessage("Registration Successfull");
		responseData.setUsername("Rahul");
		
		assertEquals(200, responseData.getCode());
		assertEquals("Registration Successfull", responseData.getMessage());
		assertEquals("Rahul", responseData.getUsername());
	
	}

}
