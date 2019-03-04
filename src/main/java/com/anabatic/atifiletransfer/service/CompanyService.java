package com.anabatic.atifiletransfer.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Service;

import com.anabatic.atifiletransfer.entities.Company;
import com.anabatic.atifiletransfer.entities.Company.StatusCompany;
import com.anabatic.atifiletransfer.repository.CompanyDao;
import com.anabatic.atifiletransfer.service.impl.ICompany;

@Service
public class CompanyService implements ICompany {

	@Autowired
	CompanyDao companyDao;
	
	@Override
	public List<Company> findAllCompany() {
		return companyDao.findAll();
	}

	@Override
	public Company findOneCompany(Long idCompany) {
		return companyDao.findOne(idCompany);
	}

	@Override
	@Modifying
	public void saveCompany(Company company) {
		companyDao.saveAndFlush(company);
	}

	@Override
	@Modifying
	public void editCompany(Company company) {
		Company companyTemp = companyDao.findOne(company.getCompanyId());
		companyTemp.setCompanyName(company.getCompanyName());
		companyTemp.setCompanyStatus(company.getCompanyStatus());
		companyDao.saveAndFlush(companyTemp);
	}

	@Override
	public List<Company> findAllCompanyOrderById() {
		return companyDao.findAllCompanyOrderById();
	}

	@Override
	@Modifying
	public void deleteCompanyById(Long idCompany) {
		companyDao.delete(idCompany);
	}

	@Override
	public Company findOneCompanyByName(String companyName) {
		return companyDao.findOneCompanyByName(companyName);
	}

	@Override
	public List<Company> findAllCompanyJoinParameterByParameterTypeAndCompanyStatusAndCompanyType(StatusCompany companyStatus, String companyParameterType) {
		return companyDao.findAllCompanyJoinParameterByParameterTypeAndCompanyStatusAndCompanyType(companyStatus,companyParameterType);
	}

	@Override
	public Company findOneCompanyJoinParameterByParameterTypeAndCompanyStatusAndCompanyType(StatusCompany companyStatus,
			String companyParameterType) {
		return companyDao.findOneCompanyJoinParameterByParameterTypeAndCompanyStatusAndCompanyType(companyStatus, companyParameterType);
	}

	@Override
	public List<Company> findAllCompanyByName(String companyName) {
		return companyDao.findAllCompanyByName(companyName);
	}

	@Override
	public List<Company> findAllCompanyByNameOrType(String companyName) {
		return companyDao.findAllCompanyByNameOrType(companyName);
	}

	@Override
	public Long findSummaryCompanyOrderById(StatusCompany companyStatus) {
		return companyDao.findSummaryCompanyOrderById(companyStatus);
	}

	@Override
	public List<Company> findAllCompanyByStatus(StatusCompany companyStatus) {
		return companyDao.findAllCompanyByStatus(companyStatus);
	}

}
