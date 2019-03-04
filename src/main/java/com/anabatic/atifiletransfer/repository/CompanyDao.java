package com.anabatic.atifiletransfer.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.anabatic.atifiletransfer.entities.Company;
import com.anabatic.atifiletransfer.entities.Company.StatusCompany;

public interface CompanyDao extends JpaRepository<Company, Long> {

	@Query("from Company order by companyId")
	public List<Company> findAllCompanyOrderById();
	
	@Query("select count(companyName) from Company where companyStatus = :companyStatus")
	public Long findSummaryCompanyOrderById(@Param(value = "companyStatus") StatusCompany companyStatus);
	
	@Query("from Company where companyStatus = :companyStatus")
	public List<Company> findAllCompanyByStatus(@Param(value = "companyStatus") StatusCompany companyStatus);
	
	@Query("from Company where companyName = :companyName")
	public Company findOneCompanyByName(@Param(value = "companyName") String companyName);
	
	@Query("from Company where companyName = :companyName")
	public List<Company> findAllCompanyByName(@Param(value = "companyName") String companyName);
	
	
	@Query("from Company where companyName = :companyName")
	public List<Company> findAllCompanyByNameOrType(@Param(value = "companyName") String companyName);
	
	@Query("select c from Company c join fetch c.companiesParameter p where c.companyStatus = :companyStatus and p.companyParameterType = :companyParameterType")
	public List<Company> findAllCompanyJoinParameterByParameterTypeAndCompanyStatusAndCompanyType(@Param(value = "companyStatus") StatusCompany companyStatus,@Param(value = "companyParameterType") String companyParameterType);
	
	@Query("select c from Company c join fetch c.companiesParameter p where c.companyStatus = :companyStatus and p.companyParameterType = :companyParameterType")
	public Company findOneCompanyJoinParameterByParameterTypeAndCompanyStatusAndCompanyType(@Param(value = "companyStatus") StatusCompany companyStatus,@Param(value = "companyParameterType") String companyParameterType);
}
