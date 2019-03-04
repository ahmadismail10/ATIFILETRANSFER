package com.anabatic.atifiletransfer.dto;

import java.util.List;

public class CoreSystemDTO {
	
	private String coreSystemId;
	
	private String coreSystemLocalDirectory;
	
	private int coreSystemTimer;
	
	private List<CompanyDTO> companies;

	public String getCoreSystemId() {
		return coreSystemId;
	}

	public void setCoreSystemId(String coreSystemId) {
		this.coreSystemId = coreSystemId;
	}

	public String getCoreSystemLocalDirectory() {
		return coreSystemLocalDirectory;
	}

	public void setCoreSystemLocalDirectory(String coreSystemLocalDirectory) {
		this.coreSystemLocalDirectory = coreSystemLocalDirectory;
	}

	public int getCoreSystemTimer() {
		return coreSystemTimer;
	}

	public void setCoreSystemTimer(int coreSystemTimer) {
		this.coreSystemTimer = coreSystemTimer;
	}

	public List<CompanyDTO> getCompanies() {
		return companies;
	}

	public void setCompanies(List<CompanyDTO> companies) {
		this.companies = companies;
	}

}
