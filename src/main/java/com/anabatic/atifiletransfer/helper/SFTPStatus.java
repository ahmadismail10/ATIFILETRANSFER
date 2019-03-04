package com.anabatic.atifiletransfer.helper;

import java.util.List;

import com.anabatic.atifiletransfer.entities.LogHistory;

public class SFTPStatus {
	
	private List<LogHistory> listHistories;
	
	private Boolean status;
	
	public SFTPStatus() {
		
	}
	
	public SFTPStatus(List<LogHistory> listHistories, Boolean status) {
		this.listHistories = listHistories;
		this.status = status;
	}

	public List<LogHistory> getListHistories() {
		return listHistories;
	}

	public void setListHistories(List<LogHistory> listHistories) {
		this.listHistories = listHistories;
	}

	public Boolean getStatus() {
		return status;
	}

	public void setStatus(Boolean status) {
		this.status = status;
	}
	
}
