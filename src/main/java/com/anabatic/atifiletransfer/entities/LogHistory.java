package com.anabatic.atifiletransfer.entities;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import org.hibernate.annotations.Type;

@Entity
@SuppressWarnings("serial")
public class LogHistory implements Serializable {
	
	@Id
	@Column(nullable = false)
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long logHistoryId;
	
	@Column(length = 50)
	private String logHistoryFileName;
	
	@Column
	private Long logHistoriesFileSize;
	
	@Column(nullable = false)
	@Type(type="date")
	private Date logHistoryDate;
	
	@Column(nullable = false)
	@Type(type="time")
	private Date logHistoryTime;
	
	@Column(nullable = false, length = 20)
	private String logHistoryStage;
	
	@Column(nullable = false, length = 20)
	private String logHistoryStatus;
	
	@Column
	@Type(type="text")
	private String logHistoryErrorMessage;
	
	@ManyToOne
	@JoinColumn(nullable = false, columnDefinition="varchar(10)")
	private CompanyParameter companyParameter;
	
	public LogHistory() {
		
	}

	public LogHistory(String logHistoryFileName, Long logHistoriesFileSize, Date logHistoryDate, Date logHistoryTime, String logHistoryStage, String logHistoryStatus, String logHistoryErrorMessage, CompanyParameter companyParameter) {
		this.logHistoryFileName = logHistoryFileName;
		this.logHistoriesFileSize = logHistoriesFileSize;
		this.logHistoryDate = logHistoryDate;
		this.logHistoryTime = logHistoryTime;
		this.logHistoryStage = logHistoryStage;
		this.logHistoryStatus = logHistoryStatus;
		this.logHistoryErrorMessage = logHistoryErrorMessage;
		this.companyParameter = companyParameter;
	}

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

	public String getLogHistoryStage() {
		return logHistoryStage;
	}

	public void setLogHistoryStage(String logHistoryStage) {
		this.logHistoryStage = logHistoryStage;
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

	public CompanyParameter getCompanyParameter() {
		return companyParameter;
	}

	public void setCompanyParameter(CompanyParameter companyParameter) {
		this.companyParameter = companyParameter;
	}

}
