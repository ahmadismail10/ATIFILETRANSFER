package com.anabatic.atifiletransfer.entities;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.PrePersist;
import javax.validation.constraints.Size;
import org.hibernate.validator.constraints.NotEmpty;

@Entity
@SuppressWarnings("serial")
public class CompanyParameter implements Serializable {
	
	@Id
	@Column(nullable = false)
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long companyParameterId;
	
	@Column(nullable = false, columnDefinition = "enum('FTP','SFTP')")
	@Enumerated(EnumType.STRING)
    private ProtocolCompany companyParameterProtocol = ProtocolCompany.SFTP;
	
	@Column(nullable = false, length = 15)
	@Size(min=5, max=15, message="{message.error.companyparameter.ip}")
	private String companyParameterIp;
	
	@Column(nullable = false)
	private int companyParameterPort;
	
	@Column(nullable = false, length = 25)
	@NotEmpty(message="{message.error.companyparameter.username.empty}")
	@Size(max=25, message="{message.error.companyparameter.username}")
	private String companyParameterUsername;
	
	@Column(nullable = false, length = 25)
	@NotEmpty(message="{message.error.companyparameter.password.empty}")
	@Size(max=25, message="{message.error.companyparameter.password}")
	private String companyParameterPassword;
	
	@Column(length = 20)
	@Size(max=20, message="{message.error.companyparameter.prefix}")
	private String companyParameterPrefix;
	
	@Column
	private String companyParameterDescription;
	
	@Column
//	@NotEmpty(message="{message.error.companyparameter.remotedirectory.empty}")
	private String companyParameterRemoteDirectory;
	
	@Column(nullable = false, length = 10)
	private String companyParameterType;
	
	@Column(nullable = false, length = 10)
	private String companyParameterFileExtension;
	
	@ManyToOne
	@JoinColumn(nullable = false)
	private Company company;
	
	@Column(name = "company_company_id", insertable = false, updatable = false)
	private Long companyId;
	
	
	@OneToMany(mappedBy="companyParameter", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<LogHistory> logHistories;
	
	public CompanyParameter() {
		
	}
	
	public enum ProtocolCompany {
		FTP,SFTP
	}
	
	@PrePersist
	public void prePersist() {
	    if(companyParameterProtocol == null) 
	    	companyParameterProtocol = ProtocolCompany.SFTP;
	}
	
	public ProtocolCompany getCompanyParameterProtocol() {
		return companyParameterProtocol;
	}

	public void setCompanyParameterProtocol(ProtocolCompany companyParameterProtocol) {
		this.companyParameterProtocol = companyParameterProtocol;
	}
	
	public CompanyParameter(Long companyParameterId) {
		this.companyParameterId = companyParameterId;
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

	public Company getCompany() {
		return company;
	}

	public void setCompany(Company company) {
		this.company = company;
	}
	
	public Long getCompanyId()
	{
		return companyId;
	}

	public List<LogHistory> getLogHistories() {
		return logHistories;
	}

	public void setLogHistories(List<LogHistory> logHistories) {
		this.logHistories = logHistories;
	}
	
}
