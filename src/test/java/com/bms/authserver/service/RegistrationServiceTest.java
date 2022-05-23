package com.bms.authserver.service;

import static org.junit.jupiter.api.Assertions.*;

import java.text.ParseException;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.bms.authserver.dao.CustomerCredentialsRepository;
import com.bms.authserver.models.AccountDetail;
import com.bms.authserver.models.CustomerCredentials;
import com.bms.authserver.pojo.RegistrationData;
import com.bms.authserver.pojo.ResponseData;

@SpringBootTest
@ExtendWith(SpringExtension.class)
class RegistrationServiceTest {
	
	@Autowired
	RegistrationService registrationService;
	
	@MockBean
	RegistrationServiceJpa controllerServiceJpa;

	@MockBean
	CustomerCredentialsRepository customerCredentialsRepository;
	
	@Test
	void validationcheck() throws Exception {
		String date="01/06/1999";
		RegistrationData data = new RegistrationData("rohith42822","rohith4282","Rajeshwar","Shivanathri","rohithshivanathri@gmail.com","male",date,"9177144155","JBVPS5610D","3-42 minaroad duudenapally","karimnagar","telangana","india","505472","savings");
		ResponseData response = new ResponseData("rohith42822","failure",300,"password doesn't meet criteria!");
		AccountDetail ad = new AccountDetail();
		CustomerCredentials cc = new CustomerCredentials();
		
		Mockito.when(controllerServiceJpa.customerdetailinsertion(data)).thenReturn(ad);
		Mockito.when(controllerServiceJpa.isUsernameAvailable("rohith42822")).thenReturn(true);
		ResponseData actualResponse = registrationService.validationcheck(data);
		
		assertEquals(response.getMessage(), actualResponse.getMessage());
		
	}
	
	@Test
	void validateFirstname() throws Exception {
		String date="01/06/1999";
		RegistrationData data = new RegistrationData("rohith42822","Rohith@4282","rajeshwar","Shivanathri","rohithshivanathri@gmail.com","male",date,"9177144155","JBVPS5610D","3-42 minaroad duudenapally","karimnagar","telangana","india","505472","savings");
		ResponseData response = new ResponseData("rohith42822","failure",300,"please enter a valid firstname!");
		AccountDetail ad = new AccountDetail();
		CustomerCredentials cc = new CustomerCredentials();
		
		Mockito.when(controllerServiceJpa.customerdetailinsertion(data)).thenReturn(ad);
		Mockito.when(controllerServiceJpa.isUsernameAvailable("rohith42822")).thenReturn(true);
		ResponseData actualResponse = registrationService.validationcheck(data);
		
		assertEquals(response.getMessage(), actualResponse.getMessage());
		
	}
	
	@Test
	void validateEmail() throws Exception {
		String date="01/06/1999";
		RegistrationData data = new RegistrationData("rohith42822","Rohith@4282","Rajeshwar","Shivanathri","rohithshivanathrigmail.com","male",date,"9177144155","JBVPS5610D","3-42 minaroad duudenapally","karimnagar","telangana","india","505472","savings");
		ResponseData response = new ResponseData("rohith42822","failure",300,"please enter a valid email id!");
		AccountDetail ad = new AccountDetail();
		CustomerCredentials cc = new CustomerCredentials();
		
		Mockito.when(controllerServiceJpa.customerdetailinsertion(data)).thenReturn(ad);
		Mockito.when(controllerServiceJpa.isUsernameAvailable("rohith42822")).thenReturn(true);
		ResponseData actualResponse = registrationService.validationcheck(data);
		
		assertEquals(response.getMessage(), actualResponse.getMessage());
		
	}
	
	@Test
	void validateDOB() throws Exception {
		String date="01/06/199";
		RegistrationData data = new RegistrationData("rohith42822","Rohith@4282","Rajeshwar","Shivanathri","rohith@shivanathrigmail.com","male",date,"9177144155","JBVPS5610D","3-42 minaroad duudenapally","karimnagar","telangana","india","505472","savings");
		ResponseData response = new ResponseData("rohith42822","failure",300,"please enter a valid dob!");
		AccountDetail ad = new AccountDetail();
		CustomerCredentials cc = new CustomerCredentials();
		
		Mockito.when(controllerServiceJpa.customerdetailinsertion(data)).thenReturn(ad);
		Mockito.when(controllerServiceJpa.isUsernameAvailable("rohith42822")).thenReturn(true);
		ResponseData actualResponse = registrationService.validationcheck(data);
		
		assertEquals(response.getMessage(), actualResponse.getMessage());
		
	}

	@Test
	void validatePhone() throws Exception {
		String date="01/06/1992";
		RegistrationData data = new RegistrationData("rohith42822","Rohith@4282","Rajeshwar","Shivanathri","rohith@shivanathrigmail.com","male",date,"91771441","JBVPS5610D","3-42 minaroad duudenapally","karimnagar","telangana","india","505472","savings");
		ResponseData response = new ResponseData("rohith42822","failure",300,"please enter a valid phone number!");
		AccountDetail ad = new AccountDetail();
		CustomerCredentials cc = new CustomerCredentials();
		
		Mockito.when(controllerServiceJpa.customerdetailinsertion(data)).thenReturn(ad);
		Mockito.when(controllerServiceJpa.isUsernameAvailable("rohith42822")).thenReturn(true);
		ResponseData actualResponse = registrationService.validationcheck(data);
		
		assertEquals(response.getMessage(), actualResponse.getMessage());
		
	}
	
	@Test
	void validatePAN() throws Exception {
		String date="01/06/1992";
		RegistrationData data = new RegistrationData("rohith42822","Rohith@4282","Rajeshwar","Shivanathri","rohith@shivanathrigmail.com","male",date,"9177144133","1BVPS5610D","3-42 minaroad duudenapally","karimnagar","telangana","india","505472","savings");
		ResponseData response = new ResponseData("rohith42822","failure",300,"please enter a valid pan number!");
		AccountDetail ad = new AccountDetail();
		CustomerCredentials cc = new CustomerCredentials();
		
		Mockito.when(controllerServiceJpa.customerdetailinsertion(data)).thenReturn(ad);
		Mockito.when(controllerServiceJpa.isUsernameAvailable("rohith42822")).thenReturn(true);
		ResponseData actualResponse = registrationService.validationcheck(data);
		
		assertEquals(response.getMessage(), actualResponse.getMessage());
		
	}

	@Test
	void validatePincode() throws Exception {
		String date="01/06/1992";
		RegistrationData data = new RegistrationData("rohith42822","Rohith@4282","Rajeshwar","Shivanathri","rohith@shivanathrigmail.com","male",date,"9177144133","ABVPS5610D","3-42 minaroad duudenapally","karimnagar","telangana","india","505","savings");
		ResponseData response = new ResponseData("rohith42822","failure",300,"please enter a valid pincode!");
		AccountDetail ad = new AccountDetail();
		CustomerCredentials cc = new CustomerCredentials();
		
		Mockito.when(controllerServiceJpa.customerdetailinsertion(data)).thenReturn(ad);
		Mockito.when(controllerServiceJpa.isUsernameAvailable("rohith42822")).thenReturn(true);
		ResponseData actualResponse = registrationService.validationcheck(data);
		
		assertEquals(response.getMessage(), actualResponse.getMessage());
		
	}
	
	@Test
	void validateAccountType() throws Exception {
		String date="01/06/1995";
		RegistrationData data = new RegistrationData("rohith42822","Rohith@4282","Rajeshwar","Shivanathri","rohith@shivanathrigmail.com","male",date,"9177144133","ABVPS5610D","3-42 minaroad duudenapally","karimnagar","telangana","india","505965","save");
		ResponseData response = new ResponseData("rohith42822","failure",300,"please enter a valid accounttype (savings/current/loanaccount)");
		AccountDetail ad = new AccountDetail();
		CustomerCredentials cc = new CustomerCredentials();
		
		Mockito.when(controllerServiceJpa.customerdetailinsertion(data)).thenReturn(ad);
		Mockito.when(controllerServiceJpa.isUsernameAvailable("rohith42822")).thenReturn(true);
		ResponseData actualResponse = registrationService.validationcheck(data);
		
		assertEquals(response.getMessage(), actualResponse.getMessage());
		
	}


}
