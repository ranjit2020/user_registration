package com.bms.authserver.controller;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.bms.authserver.pojo.RegistrationData;
import com.bms.authserver.pojo.ResponseData;
import com.bms.authserver.service.RegistrationService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;


@SpringBootTest
@ExtendWith(SpringExtension.class)
@AutoConfigureMockMvc
class RegistrationControllerTest {
	
	@Autowired
	private ObjectMapper MAPPER;
	
	@Autowired
	private MockMvc mockMvc;
	
	@MockBean
	RegistrationService controllerservice ;

	@DisplayName(value = "Successfull Registration")
	@Test
	void successfullRegistration() throws Exception {

		String date="01/06/1999";
		RegistrationData data = new RegistrationData("rohith2767","Rohith@4282","Rajeshwar","Shivanathri","rohithshivanathri@gmail.com","male",date,"9177144155","JBVPS5610D","3-42 minaroad duudenapally","karimnagar","telangana","india","505472","savings");
		ResponseData response = new ResponseData("rohith2767","success",200,"successfully registered!");	
		String json = MAPPER.writeValueAsString(data);
		String expeactedResponse = MAPPER.writeValueAsString(response);
		
		Mockito.when(controllerservice.validationcheck(Mockito.any(RegistrationData.class))).thenReturn(response);
		 MvcResult mvcResult = mockMvc
				.perform(MockMvcRequestBuilders.post("/register")
						.content(json).contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON))
				.andReturn();
		    assertEquals(expeactedResponse, mvcResult.getResponse().getContentAsString());
	
	}
	
	//testing for blank field
	@DisplayName(value = "Empty Field")
	@Test
	void check1() throws Exception {
		String date="01/06/1999";
		RegistrationData data = new RegistrationData("","Rohith@4282","Rajeshwar","Shivanathri","rohithshivanathri@gmail.com","male",date,"9177144155","JBVPS5610D","3-42 minaroad duudenapally","karimnagar","telangana","india","505472","savings");
		ResponseData response = new ResponseData("","failure",404,"username,cannot be empty");	
		
		String json = MAPPER.writeValueAsString(data);
		String json1 = MAPPER.writeValueAsString(response);
		
		 when(controllerservice.validationcheck(data)).thenReturn(response);
		MvcResult mvcResult = mockMvc
				.perform(MockMvcRequestBuilders.post("/register")
						.content(json).contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON))
				.andReturn();
	    assertEquals(json1, mvcResult.getResponse().getContentAsString());
		
	}
	
	//testing for password validation
	@DisplayName(value = "Password validation")
	@Test
	 void check2() throws Exception {
		String date="01/06/1999";
		RegistrationData data = new RegistrationData("rohith42822","rohith4282","Rajeshwar","Shivanathri","rohithshivanathri@gmail.com","male",date,"9177144155","JBVPS5610D","3-42 minaroad duudenapally","karimnagar","telangana","india","505472","savings");
		ResponseData response = new ResponseData("rohith42822","failure",300,"password doesn't meet criteria!");
		
		String json = MAPPER.writeValueAsString(data);
		String json1 = MAPPER.writeValueAsString(response);
		Mockito.when(controllerservice.validationcheck(Mockito.any(RegistrationData.class))).thenReturn(response);
		MvcResult mvcResult = mockMvc
				.perform(MockMvcRequestBuilders.post("/register")
						.content(json).contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON))
				.andReturn();
	    assertEquals(json1, mvcResult.getResponse().getContentAsString());
	    
	}
	//testing for email id validation
	@DisplayName(value = "Email validation")
	@Test
	 void check3() throws Exception {
		String date="01/06/1999";
		RegistrationData data = new RegistrationData("rohith42823","Rohith@4282","Rajeshwar","Shivanathri","rohithshivanathgmail.com","male",date,"9177144155","JBVPS5610D","3-42 minaroad duudenapally","karimnagar","telangana","india","505472","savings");
		ResponseData response = new ResponseData("rohith42823","failure",300,"please enter a valid email id!");
		
		String json = MAPPER.writeValueAsString(data);
		String json1 = MAPPER.writeValueAsString(response);
		
		Mockito.when(controllerservice.validationcheck(Mockito.any(RegistrationData.class))).thenReturn(response);
		
		MvcResult mvcResult = mockMvc
				.perform(MockMvcRequestBuilders.post("/register")
						.content(json).contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON))
				.andReturn();
	    assertEquals(json1, mvcResult.getResponse().getContentAsString());
	}
	//test case for phone number validation
	@DisplayName(value = "Phone Number validation")
	@Test
	 void check4() throws Exception {
		String date="01/06/1999";
		RegistrationData data = new RegistrationData("rohith42824","Rohith@4282","Rajeshwar","Shivanathri","rohithshivanath@gmail.com","male",date,"91771155","JBVPS5610D","3-42 minaroad duudenapally","karimnagar","telangana","india","505472","savings");
		ResponseData response = new ResponseData("rohith42824","failure",300,"please enter a valid phone number!");
		String json = MAPPER.writeValueAsString(data);
		String json1 = MAPPER.writeValueAsString(response);
		
		Mockito.when(controllerservice.validationcheck(Mockito.any(RegistrationData.class))).thenReturn(response);
		MvcResult mvcResult = mockMvc
				.perform(MockMvcRequestBuilders.post("/register")
						.content(json).contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON))
				.andReturn();
	    assertEquals(json1, mvcResult.getResponse().getContentAsString());
	}

	@DisplayName(value = "PAN validation")
	@Test
	 void check5() throws Exception {
		String date="01/06/1999";
		RegistrationData data = new RegistrationData("rohith42825","Rohith@4282","Rajeshwar","Shivanathri","rohithshivanath@gmail.com","male",date,"9177144155","JBVPS56D","3-42 minaroad duudenapally","karimnagar","telangana","india","505472","current");
		ResponseData response = new ResponseData("rohith42825","failure",300,"please enter a valid pan number!");
		String json = MAPPER.writeValueAsString(data);
		String json1 = MAPPER.writeValueAsString(response);
		
		Mockito.when(controllerservice.validationcheck(Mockito.any(RegistrationData.class))).thenReturn(response);
		
		MvcResult mvcResult = mockMvc
				.perform(MockMvcRequestBuilders.post("/register")
						.content(json).contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON))
				.andReturn();
	    assertEquals(json1, mvcResult.getResponse().getContentAsString());
	}

	@DisplayName(value = "Pincode validation")
	@Test
	 void check6() throws Exception {
		String date="01/06/1999";
		RegistrationData data = new RegistrationData("rohith42826","Rohith@4282","Rajeshwar","Shivanathri","rohithshivanath@gmail.com","male",date,"9177144155","JBVPS5610D","3-42 minaroad duudenapally","karimnagar","telangana","india","50572","loanaccount");
		ResponseData response = new ResponseData("rohith42826","failure",300,"please enter a valid pincode!");
		String json = MAPPER.writeValueAsString(data);
		String json1 = MAPPER.writeValueAsString(response);
		
		Mockito.when(controllerservice.validationcheck(Mockito.any(RegistrationData.class))).thenReturn(response);
		
		MvcResult mvcResult = mockMvc
				.perform(MockMvcRequestBuilders.post("/register")
						.content(json).contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON))
				.andReturn();
	    assertEquals(json1, mvcResult.getResponse().getContentAsString());
	}

	@DisplayName(value = "Multiple validation")
	@Test
	 void check8() throws Exception {
		String date="01/06/1999";
		RegistrationData data = new RegistrationData("rohith42827","rohith4282","Rajeshwar","Shivanathri","rohithshivanathrigmail.com","male",date,"9144155","JBVPS50D","3-42 minaroad duudenapally","karimnagar","telangana","india","505472","savings");
		ResponseData response = new ResponseData("rohith42827","failure",300,"password doesn't meet criteria!please enter a valid email id!please enter a valid phone number!please enter a valid pan number!");
		String json = MAPPER.writeValueAsString(data);
		String json1 = MAPPER.writeValueAsString(response);
		
		Mockito.when(controllerservice.validationcheck(Mockito.any(RegistrationData.class))).thenReturn(response);
		
		MvcResult mvcResult = mockMvc
				.perform(MockMvcRequestBuilders.post("/register")
						.content(json).contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON))
				.andReturn();
	    assertEquals(json1, mvcResult.getResponse().getContentAsString());
	}
	
	@DisplayName(value = "DOB validation")
	@Test
	 void check9() throws Exception {
		String date="01/06/199";
		RegistrationData data = new RegistrationData("rohith42828","Rohith@4282","Rajeshwar","Shivanathri","rohithshivanath@gmail.com","male",date,"9177144155","JBVPS5610D","3-42 minaroad duudenapally","karimnagar","telangana","india","505472","savings");
		ResponseData response = new ResponseData("rohith42828","failure",300,"please enter a valid dob!");
		String json = MAPPER.writeValueAsString(data);
		String json1 = MAPPER.writeValueAsString(response);
		
		Mockito.when(controllerservice.validationcheck(Mockito.any(RegistrationData.class))).thenReturn(response);
		
		MvcResult mvcResult = mockMvc
				.perform(MockMvcRequestBuilders.post("/register")
						.content(json).contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON))
				.andReturn();
	    assertEquals(json1, mvcResult.getResponse().getContentAsString());
	}

	@DisplayName(value = "Account Type validation")
	@Test
	 void check10() throws Exception {
		String date="01/06/1999";
		RegistrationData data = new RegistrationData("rohith42829","Rohith@4282","Rajeshwar","Shivanathri","rohithshivanathri@gmail.com","male",date,"9177144155","JBVPS5610D","3-42 minaroad duudenapally","karimnagar","telangana","india","505472","nothing");
		ResponseData response = new ResponseData("rohith42829","failure",300,"please enter a valid accounttype (savings/current/loanaccount)");	
		String json = MAPPER.writeValueAsString(data);
		String json1 = MAPPER.writeValueAsString(response);
		
		Mockito.when(controllerservice.validationcheck(Mockito.any(RegistrationData.class))).thenReturn(response);
		
		MvcResult mvcResult = mockMvc
				.perform(MockMvcRequestBuilders.post("/register")
						.content(json).contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON))
				.andReturn();
	    assertEquals(json1, mvcResult.getResponse().getContentAsString());
	}
	
	@DisplayName(value = "Firstname validation")
	@Test
	 void check11() throws Exception {
		String date="01/06/1999";
		RegistrationData data = new RegistrationData("rohith428210","Rohith@4282","rajes1hwar","Shivanathri","rohithshivanathri@gmail.com","male",date,"9177144155","JBVPS5610D","3-42 minaroad duudenapally","karimnagar","telangana","india","505472","savings");
		ResponseData response = new ResponseData("rohith428210","failure",300,"please enter a valid firstname!");	
		String json = MAPPER.writeValueAsString(data);
		String json1 = MAPPER.writeValueAsString(response);
		
		Mockito.when(controllerservice.validationcheck(Mockito.any(RegistrationData.class))).thenReturn(response);
		
		MvcResult mvcResult = mockMvc
				.perform(MockMvcRequestBuilders.post("/register")
						.content(json).contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON))
				.andReturn();
	    assertEquals(json1, mvcResult.getResponse().getContentAsString());
	}
	
	@DisplayName(value = "Lastname & PAN validation")
	@Test
	 void check12() throws Exception {
		String date="01/06/1999";
		RegistrationData data = new RegistrationData("rohith428211","Rohith@4282","Rajeshwar","shivanathri","rohtihshivanatrhi@gmail.com","male",date,"9177144155","jbvps5610d","3-42 minaroad duudenapally","karimnagar","telangana","india","505472","savings");
		ResponseData response = new ResponseData("rohith428211","failure",300,"please enter a valid lastname!please enter a valid pan number!");	
		String json = MAPPER.writeValueAsString(data);
		String json1 = MAPPER.writeValueAsString(response);
		
		Mockito.when(controllerservice.validationcheck(Mockito.any(RegistrationData.class))).thenReturn(response);
		
		MvcResult mvcResult = mockMvc
				.perform(MockMvcRequestBuilders.post("/register")
						.content(json).contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON))
				.andReturn();
	    assertEquals(json1, mvcResult.getResponse().getContentAsString());
	}

}
