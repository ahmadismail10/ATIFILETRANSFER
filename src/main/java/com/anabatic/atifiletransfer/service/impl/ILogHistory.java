package com.anabatic.atifiletransfer.service.impl;

import java.util.List;

import com.anabatic.atifiletransfer.entities.LogHistory;

public interface ILogHistory {
	
	public List<LogHistory> findAllLogHistory();
	
	public LogHistory findOneLogHistory(Long logHistoryId);
	
	public void saveLogHistory(LogHistory logHistory);
	
	public void editLogHistory(LogHistory logHistory);
	
	public void deleteLogHistoryById(Long logHistoryId);
	
	public void saveLogHistories(List<LogHistory> logHistories);
	
	public List<LogHistory> findAllLogHistoryOrderByDateAndTime();
	
	public List<LogHistory> findAllLogHistoryOrderByDateAndTimeJoinCompanyParameterAndCompany();

	public Long findSummaryLogHistorySuccess(String logHistoryStatus);
	
	public List<LogHistory> findAllLogHistoryByStatusOrderByDateAndTime(String logHistoryStatus);
}
