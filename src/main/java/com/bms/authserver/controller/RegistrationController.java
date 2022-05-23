package com.bms.authserver.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.bms.authserver.pojo.RegistrationData;
import com.bms.authserver.pojo.ResponseData;
import com.bms.authserver.service.RegistrationService;
import com.bms.authserver.service.RegistrationServiceJpa;

@RestController
public class RegistrationController {
	
	
    @Autowired
    RegistrationServiceJpa controllerServiceJpa;
	@Autowired
    RegistrationService registrationService ;
	
	
	@PostMapping("/register")
	public ResponseData registerNewUser(@Valid @RequestBody RegistrationData registrationData, BindingResult bindingResult)  {
		
		if(bindingResult.hasErrors()) {
		List<FieldError> list = bindingResult.getFieldErrors();
		StringBuilder sb = new StringBuilder();
		for(FieldError i:list) {
			 sb.append(i.getField()+",");
		}
			return new ResponseData(registrationData.getUsername(),"failure",404,sb+"cannot be empty");
		}
		
		return registrationService.validationcheck(registrationData);
	}
}
	