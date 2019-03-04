package com.anabatic.atifiletransfer.service.impl;

import java.util.List;

import com.anabatic.atifiletransfer.entities.CompanyParameter;

public interface ICompanyParameter {

	public List<CompanyParameter> findAllCompanyParameter();
	
	public CompanyParameter findOneCompanyParameter(Long companyParameterId);
	
	public void saveCompanyParameter(CompanyParameter companyParameter);
	
	public void editCompanyParameter(CompanyParameter companyParameter);
	
	public List<CompanyParameter> findAllCompanyParameterOrderById();
	
	public void deleteCompanyParameterById(Long companyParameterId);
	
	public void saveManyCompanyParameter(List<CompanyParameter> companiesParamter);
	
	public CompanyParameter findCompanyParameterByTypeAndCompanyId(String companyParameterType, Long companyId);
	
	public Long findSummaryCompanyParameter();
}
