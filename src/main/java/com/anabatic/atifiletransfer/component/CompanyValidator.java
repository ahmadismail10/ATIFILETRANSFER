package com.anabatic.atifiletransfer.component;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.anabatic.atifiletransfer.entities.Company;
import com.anabatic.atifiletransfer.service.CompanyService;

@Component
public class CompanyValidator implements Validator{

	@Autowired
	CompanyService companySvc;
	
	@Override
	public boolean supports(Class<?> clazz) {
		return clazz == Company.class;
	}

	@Override
	public void validate(Object target, Errors errors) {
		Company company = (Company) target;
		if(companySvc.findOneCompanyByName(company.getCompanyName()) != null && companySvc.findOneCompanyByName(company.getCompanyName()).getCompanyId() != company.getCompanyId()) {
			errors.rejectValue("companyName", "message.error.company.exist");
		}
	}

}
