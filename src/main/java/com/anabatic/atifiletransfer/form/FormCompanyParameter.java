package com.anabatic.atifiletransfer.form;

import java.util.List;

import javax.validation.Valid;

import com.anabatic.atifiletransfer.entities.CompanyParameter;

public class FormCompanyParameter {
	
	@Valid
	private List<CompanyParameter> companiesParameter;

	public FormCompanyParameter() {
		
	}
	
	public List<CompanyParameter> getCompaniesParameter() {
		return companiesParameter;
	}

	public void setCompaniesParameter(List<CompanyParameter> companiesParameter) {
		this.companiesParameter = companiesParameter;
	}

}
