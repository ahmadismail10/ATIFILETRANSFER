package com.anabatic.atifiletransfer.dto;

import java.util.List;

import com.anabatic.atifiletransfer.entities.Company.StatusCompany;

public class CompanyDTO {
	
	private Long companyId;

	private String companyName;

	private StatusCompany companyStatus;
	
	private List<CompanyParameterDTO> companiesParameter;
	
	public CompanyDTO() {
		
	}
	
	public CompanyDTO(Long companyId, String companyName, StatusCompany companyStatus) {
		this.companyId = companyId;
		this.companyName = companyName;
		this.companyStatus = companyStatus;
	}
	
	public CompanyDTO(Long companyId, String companyName, StatusCompany companyStatus,
			List<CompanyParameterDTO> companiesParameter) {
		this.companyId = companyId;
		this.companyName = companyName;
		this.companyStatus = companyStatus;
		this.companiesParameter = companiesParameter;
	}

	public Long getCompanyId() {
		return companyId;
	}

	public void setCompanyId(Long companyId) {
		this.companyId = companyId;
	}

	public String getCompanyName() {
		return companyName;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}

	public StatusCompany getCompanyStatus() {
		return companyStatus;
	}

	public void setCompanyStatus(StatusCompany companyStatus) {
		this.companyStatus = companyStatus;
	}

	public List<CompanyParameterDTO> getCompaniesParameter() {
		return companiesParameter;
	}

	public void setCompaniesParameter(List<CompanyParameterDTO> companiesParameter) {
		this.companiesParameter = companiesParameter;
	}

}
