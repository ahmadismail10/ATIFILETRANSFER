package com.anabatic.atifiletransfer.dto;

import java.util.Date;

public class LogHistoryDTO {
	
	private Long logHistoryId;

	private String logHistoryFileName;
	
	private Long logHistoriesFileSize;
	
	private Date logHistoryDate;
	
	private Date logHistoryTime;
	
	private String logHistoryStatus;
	
	private String logHistoryStage;
	
	private String logHistoryErrorMessage;
	
	private CompanyParameterDTO  companyParameterDTO;
	
	public Long getLogHistoryId() {
		return logHistoryId;
	}	
	
	public void setLogHistoryId(Long logHistoryId) {
		this.logHistoryId = logHistoryId;
	}

	public String getLogHistoryFileName() {
		return logHistoryFileName;
	}

	public void setLogHistoryFileName(String logHistoryFileName) {
		this.logHistoryFileName = logHistoryFileName;
	}

	public Long getLogHistoriesFileSize() {
		return logHistoriesFileSize;
	}

	public void setLogHistoriesFileSize(Long logHistoriesFileSize) {
		this.logHistoriesFileSize = logHistoriesFileSize;
	}

	public Date getLogHistoryDate() {
		return logHistoryDate;
	}

	public void setLogHistoryDate(Date logHistoryDate) {
		this.logHistoryDate = logHistoryDate;
	}

	public Date getLogHistoryTime() {
		return logHistoryTime;
	}

	public void setLogHistoryTime(Date logHistoryTime) {
		this.logHistoryTime = logHistoryTime;
	}

	public String getLogHistoryStatus() {
		return logHistoryStatus;
	}

	public void setLogHistoryStatus(String logHistoryStatus) {
		this.logHistoryStatus = logHistoryStatus;
	}

	public String getLogHistoryErrorMessage() {
		return logHistoryErrorMessage;
	}

	public void setLogHistoryErrorMessage(String logHistoryErrorMessage) {
		this.logHistoryErrorMessage = logHistoryErrorMessage;
	}

	public String getLogHistoryStage() {
		return logHistoryStage;
	}

	public void setLogHistoryStage(String logHistoryStage) {
		this.logHistoryStage = logHistoryStage;
	}

	public CompanyParameterDTO getCompanyParameterDTO() {
		return companyParameterDTO;
	}

	public void setCompanyParameterDTO(CompanyParameterDTO companyParameterDTO) {
		this.companyParameterDTO = companyParameterDTO;
	}

}
