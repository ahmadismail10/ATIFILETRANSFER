package com.anabatic.atifiletransfer.dto;

import java.util.List;

public class CompanyParameterDTO {

	private Long companyParameterId;
	
	private String companyParameterIp;
	
	private int companyParameterPort;
	
	private String companyParameterUsername;
	
	private String companyParameterPassword;
	
	private String companyParameterPrefix;
	
	private String companyParameterDescription;
	
	private String companyParameterRemoteDirectory;
	
	private String companyParameterType;
	
	private String companyParameterFileExtension;
	
	private List<LogHistoryDTO> logHistories;
	
	private CompanyDTO companyDTO;
	
	public CompanyParameterDTO() {
		
	}

	public CompanyParameterDTO(Long companyParameterId, String companyParameterIp, int companyParameterPort,
			String companyParameterUsername, String companyParameterPassword, String companyParameterPrefix,
			String companyParameterDescription, String companyParameterRemoteDirectory, String companyParameterType,
			String companyParameterFileExtension, CompanyDTO companyDTO) {
		this.companyParameterId = companyParameterId;
		this.companyParameterIp = companyParameterIp;
		this.companyParameterPort = companyParameterPort;
		this.companyParameterUsername = companyParameterUsername;
		this.companyParameterPassword = companyParameterPassword;
		this.companyParameterPrefix = companyParameterPrefix;
		this.companyParameterDescription = companyParameterDescription;
		this.companyParameterRemoteDirectory = companyParameterRemoteDirectory;
		this.companyParameterType = companyParameterType;
		this.companyParameterFileExtension = companyParameterFileExtension;
		this.companyDTO = companyDTO;
	}

	public Long getCompanyParameterId() {
		return companyParameterId;
	}

	public void setCompanyParameterId(Long companyParameterId) {
		this.companyParameterId = companyParameterId;
	}

	public String getCompanyParameterIp() {
		return companyParameterIp;
	}

	public void setCompanyParameterIp(String companyParameterIp) {
		this.companyParameterIp = companyParameterIp;
	}

	public int getCompanyParameterPort() {
		return companyParameterPort;
	}

	public void setCompanyParameterPort(int companyParameterPort) {
		this.companyParameterPort = companyParameterPort;
	}

	public String getCompanyParameterUsername() {
		return companyParameterUsername;
	}

	public void setCompanyParameterUsername(String companyParameterUsername) {
		this.companyParameterUsername = companyParameterUsername;
	}

	public String getCompanyParameterPassword() {
		return companyParameterPassword;
	}

	public void setCompanyParameterPassword(String companyParameterPassword) {
		this.companyParameterPassword = companyParameterPassword;
	}

	public String getCompanyParameterPrefix() {
		return companyParameterPrefix;
	}

	public void setCompanyParameterPrefix(String companyParameterPrefix) {
		this.companyParameterPrefix = companyParameterPrefix;
	}

	public String getCompanyParameterDescription() {
		return companyParameterDescription;
	}

	public void setCompanyParameterDescription(String companyParameterDescription) {
		this.companyParameterDescription = companyParameterDescription;
	}

	public String getCompanyParameterRemoteDirectory() {
		return companyParameterRemoteDirectory;
	}

	public void setCompanyParameterRemoteDirectory(String companyParameterRemoteDirectory) {
		this.companyParameterRemoteDirectory = companyParameterRemoteDirectory;
	}

	public String getCompanyParameterType() {
		return companyParameterType;
	}

	public void setCompanyParameterType(String companyParameterType) {
		this.companyParameterType = companyParameterType;
	}

	public String getCompanyParameterFileExtension() {
		return companyParameterFileExtension;
	}

	public void setCompanyParameterFileExtension(String companyParameterFileExtension) {
		this.companyParameterFileExtension = companyParameterFileExtension;
	}

	public List<LogHistoryDTO> getLogHistories() {
		return logHistories;
	}

	public void setLogHistories(List<LogHistoryDTO> logHistories) {
		this.logHistories = logHistories;
	}

	public CompanyDTO getCompanyDTO() {
		return companyDTO;
	}

	public void setCompanyDTO(CompanyDTO companyDTO) {
		this.companyDTO = companyDTO;
	}

}
