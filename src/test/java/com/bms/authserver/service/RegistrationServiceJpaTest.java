package com.bms.authserver.service;

import java.text.SimpleDateFormat;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.bms.authserver.dao.AccountDetailRepository;
import com.bms.authserver.dao.AccountMasterRepository;
import com.bms.authserver.dao.CustomerAddressRepository;
import com.bms.authserver.dao.CustomerCredentialsRepository;
import com.bms.authserver.dao.CustomerDetailRepository;
import com.bms.authserver.models.AccountMaster;
import com.bms.authserver.models.CustomerAddress;
import com.bms.authserver.models.CustomerCredentials;
import com.bms.authserver.models.CustomerDetail;
import com.bms.authserver.pojo.RegistrationData;
import com.bms.authserver.util.CommonUtils;

import junit.framework.Assert;


@SpringBootTest
@ExtendWith(SpringExtension.class)
class RegistrationServiceJpaTest {
	
	@MockBean
	CustomerCredentialsRepository customerCredentialsRepository;
	
	@MockBean
	CustomerAddressRepository   customerAddressRepository;
	
	@MockBean
	CustomerDetailRepository   customerDetailRepository;
	
	@MockBean
	AccountDetailRepository accountDetailRepository;
	
	@MockBean
	AccountMasterRepository accountMasterRepository;
	
	
	@Autowired
	RegistrationServiceJpa registrationServiceJpa;

	@SuppressWarnings("deprecation")
	@Test
	void customerdetailinsertionTest() throws Exception {
		
		String date="01/06/1999";
		RegistrationData data = new RegistrationData("rohith42822","rohith4282","Rajeshwar","Shivanathri","rohithshivanathri@gmail.com","male",date,"9177144155","JBVPS5610D","3-42 minaroad duudenapally","karimnagar","telangana","india","505472","savings");
		CustomerCredentials cc = new CustomerCredentials(data.getUsername(),CommonUtils.bcryptPasswordEncoder(data.getPassword()));
		CustomerDetail cd= new CustomerDetail(cc,data.getFirstName(),data.getLastName(),data.getEmail(),data.getGender(), new SimpleDateFormat("dd/MM/yyyy").parse(data.getDob()),data.getContact(),data.getPan());
		CustomerAddress ca = new CustomerAddress(cd,data.getAddress(),data.getCity(),data.getState(),data.getPincode(),data.getCountry(),"active");
		AccountMaster accountMaster = new AccountMaster();
		Mockito.when(customerAddressRepository.save(ca)).thenReturn(ca);
		Mockito.when(accountMasterRepository.findByAccountType("savings")).thenReturn(accountMaster);
	    Assert.assertTrue(registrationServiceJpa.customerdetailinsertion(data)!=null);
	
	}
	
	@SuppressWarnings("deprecation")
	@Test
	void isUsernameAvailableTest() {
		CustomerCredentials cc = new CustomerCredentials();
		Mockito.when(customerCredentialsRepository.findByUsername("Ranjit123")).thenReturn(cc);
		registrationServiceJpa.isUsernameAvailable("Ranjit123");
		
		Assert.assertFalse(registrationServiceJpa.isUsernameAvailable("Ranjit123"));
		
	}

}
