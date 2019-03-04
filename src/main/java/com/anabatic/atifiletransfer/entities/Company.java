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
public class Company implements Serializable {

	@Id
	@Column(nullable = false)
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long companyId;
	
	@Column(nullable = false, length = 25)
	@NotEmpty(message="{message.error.company.name.empty}")
	@Size(max=15, message="{message.error.company.name}")
	private String companyName;
	
	@Column(nullable = false, columnDefinition = "enum('Active','NonActive')")
	@Enumerated(EnumType.STRING)
    private StatusCompany companyStatus = StatusCompany.NonActive;
	
	@ManyToOne
	@JoinColumn(nullable = false)
	private User user;
	
	@ManyToOne
	@JoinColumn(nullable = false, columnDefinition="varchar(10)")
	private CoreSystem coreSystem;
	
	@OneToMany(mappedBy="company",  cascade = CascadeType.ALL, orphanRemoval = true)
	private List<CompanyParameter> companiesParameter;

	public Company(){
		
	}
	
	public enum StatusCompany {
		Active,NonActive
	}
	
	@PrePersist
	public void prePersist() {
	    if(companyStatus == null) 
	    	companyStatus = StatusCompany.NonActive;
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

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public CoreSystem getCoreSystem() {
		return coreSystem;
	}

	public void setCoreSystem(CoreSystem coreSystem) {
		this.coreSystem = coreSystem;
	}

	public List<CompanyParameter> getCompaniesParameter() {
		return companiesParameter;
	}

	public void setCompaniesParameter(List<CompanyParameter> companiesParameter) {
		this.companiesParameter = companiesParameter;
	}
	
}
