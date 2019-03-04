package com.anabatic.atifiletransfer.service.impl;

import java.util.List;

import com.anabatic.atifiletransfer.entities.Company;
import com.anabatic.atifiletransfer.entities.Company.StatusCompany;


public interface ICompany {
	
	public List<Company> findAllCompany();
	
	public Company findOneCompany(Long companyId);
	
	public void saveCompany(Company company);
	
	public void editCompany(Company company);
	
	public List<Company> findAllCompanyOrderById();
	
	public void deleteCompanyById(Long companyId);
	
	public Company findOneCompanyByName(String companyName);
	
	public List<Company> findAllCompanyByNameOrType(String companyName);
	
	public List<Company> findAllCompanyJoinParameterByParameterTypeAndCompanyStatusAndCompanyType(StatusCompany companyStatus, String companyParameterType);
	
	public Company findOneCompanyJoinParameterByParameterTypeAndCompanyStatusAndCompanyType(StatusCompany companyStatus, String companyParameterType);

	public List<Company> findAllCompanyByName(String companyName);
	
	public Long findSummaryCompanyOrderById(StatusCompany companyStatus);
	
	public List<Company> findAllCompanyByStatus(StatusCompany companyStatus);
}
