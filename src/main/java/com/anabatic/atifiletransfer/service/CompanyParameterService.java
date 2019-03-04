package com.anabatic.atifiletransfer.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.anabatic.atifiletransfer.entities.CompanyParameter;
import com.anabatic.atifiletransfer.repository.CompanyParameterDao;
import com.anabatic.atifiletransfer.service.impl.ICompanyParameter;

@Service
public class CompanyParameterService implements ICompanyParameter {

	@Autowired
	CompanyParameterDao companyParameterDao;
	
	@Override
	public List<CompanyParameter> findAllCompanyParameter() {
		return companyParameterDao.findAll();
	}

	@Override
	public CompanyParameter findOneCompanyParameter(Long companyParameterId) {
		return companyParameterDao.findOne(companyParameterId);
	}

	@Override
	@Modifying
	public void saveCompanyParameter(CompanyParameter companyParameter) {
		companyParameterDao.saveAndFlush(companyParameter);
	}

	@Override
	@Modifying
	public void editCompanyParameter(CompanyParameter companyParameter) {
		CompanyParameter companyParameterTemp = companyParameterDao.findOne(companyParameter.getCompanyParameterId());
		companyParameterTemp.setCompanyParameterDescription(companyParameter.getCompanyParameterDescription());
		companyParameterTemp.setCompanyParameterFileExtension(companyParameter.getCompanyParameterFileExtension());
		companyParameterTemp.setCompanyParameterIp(companyParameter.getCompanyParameterIp());
		companyParameterTemp.setCompanyParameterPassword(companyParameter.getCompanyParameterPassword());
		companyParameterTemp.setCompanyParameterPort(companyParameter.getCompanyParameterPort());
		companyParameterTemp.setCompanyParameterPrefix(companyParameter.getCompanyParameterPrefix());
		companyParameterTemp.setCompanyParameterRemoteDirectory(companyParameter.getCompanyParameterRemoteDirectory());
		companyParameterTemp.setCompanyParameterType(companyParameter.getCompanyParameterType());
		companyParameterTemp.setCompanyParameterUsername(companyParameter.getCompanyParameterUsername());
		companyParameterDao.saveAndFlush(companyParameterTemp);
	}

	@Override
	public List<CompanyParameter> findAllCompanyParameterOrderById() {
		return companyParameterDao.findAllCompanyParameterOrderById();
	}

	@Override
	public void deleteCompanyParameterById(Long companyParameterId) {
		companyParameterDao.delete(companyParameterId);
	}

	@Override
	@Transactional
	public void saveManyCompanyParameter(List<CompanyParameter> companiesParamter) {
		companyParameterDao.save(companiesParamter);
	}

	@Override
	public CompanyParameter findCompanyParameterByTypeAndCompanyId(String companyParameterType, Long companyId) {
		return companyParameterDao.findCompanyParameterByTypeAndCompanyId(companyParameterType, companyId);
	}

	@Override
	public Long findSummaryCompanyParameter() {
		return companyParameterDao.findSummaryCompanyParameter();
	}

}
