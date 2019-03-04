package com.anabatic.atifiletransfer.entities;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.validation.constraints.Min;
import org.hibernate.validator.constraints.NotEmpty;


@Entity
@SuppressWarnings("serial")
public class CoreSystem implements Serializable {
	
	@Id
	@Column(nullable = false, length = 10)
	private String coreSystemId;
	
	@Column(nullable = false)
	@NotEmpty(message="{message.error.coresystem.localdirectory}")
	private String coreSystemLocalDirectory;
	
	@Column(nullable = false)
	@Min(1)
	private int coreSystemTimer;
	
	@OneToMany(mappedBy="coreSystem")
	private List<Company> companies;

	public CoreSystem() {
		
	}
	
	public String getCoreSystemId() {
		return coreSystemId;
	}

	public void setCoreSystemId(String coreSystemId) {
		this.coreSystemId = coreSystemId;
	}

	public int getCoreSystemTimer() {
		return coreSystemTimer;
	}

	public void setCoreSystemTimer(int coreSystemTimer) {
		this.coreSystemTimer = coreSystemTimer;
	}

	public List<Company> getCompanies() {
		return companies;
	}

	public void setCompanies(List<Company> companies) {
		this.companies = companies;
	}

	public String getCoreSystemLocalDirectory() {
		return coreSystemLocalDirectory;
	}

	public void setCoreSystemLocalDirectory(String coreSystemLocalDirectory) {
		this.coreSystemLocalDirectory = coreSystemLocalDirectory;
	}

}
