package com.anabatic.atifiletransfer.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.anabatic.atifiletransfer.entities.CompanyParameter;

public interface CompanyParameterDao extends JpaRepository<CompanyParameter, Long> {

	@Query("from CompanyParameter order by companyParameterId")
	public List<CompanyParameter> findAllCompanyParameterOrderById();
	
	@Query("select count(companyParameterId) from CompanyParameter")
	public Long findSummaryCompanyParameter();
	
	@Query("from CompanyParameter where companyParameterType = :companyParameterType and company.companyId = :companyId")
	public CompanyParameter findCompanyParameterByTypeAndCompanyId(@Param(value = "companyParameterType") String companyParameterType,@Param(value = "companyId") Long companyId);
	
}
