package com.anabatic.atifiletransfer.service;

import java.util.List;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Service;

import com.anabatic.atifiletransfer.entities.LogHistory;
import com.anabatic.atifiletransfer.repository.LogHistoryDao;
import com.anabatic.atifiletransfer.service.impl.ILogHistory;

@Service
public class LogHistoryService implements ILogHistory {

	@Autowired
	LogHistoryDao logHistoryDao;
	
	@Override
	public List<LogHistory> findAllLogHistory() {
		return logHistoryDao.findAll();
	}

	@Override
	public LogHistory findOneLogHistory(Long logHistoryId) {
		return logHistoryDao.findOne(logHistoryId);
	}

	@Override
	@Modifying
	public void saveLogHistory(LogHistory logHistory) {
		logHistoryDao.saveAndFlush(logHistory);
	}
	
	@Override
	@Modifying
	@Transactional
	public void saveLogHistories(List<LogHistory> logHistories) {
		logHistoryDao.save(logHistories);
	}

	@Override
	@Modifying
	public void editLogHistory(LogHistory logHistory) {
		LogHistory logHistoryTemp = logHistoryDao.findOne(logHistory.getLogHistoryId());
		logHistoryTemp.setLogHistoriesFileSize(logHistory.getLogHistoriesFileSize());
		logHistoryTemp.setLogHistoryDate(logHistory.getLogHistoryDate());
		logHistoryTemp.setLogHistoryErrorMessage(logHistory.getLogHistoryErrorMessage());
		logHistoryTemp.setLogHistoryFileName(logHistory.getLogHistoryFileName());
		logHistoryTemp.setLogHistoryStatus(logHistory.getLogHistoryStatus());
		logHistoryTemp.setLogHistoryTime(logHistory.getLogHistoryTime());
		logHistoryDao.saveAndFlush(logHistoryTemp);
	}

	@Override
	@Modifying
	public void deleteLogHistoryById(Long logHistoryId) {
		logHistoryDao.delete(logHistoryId);
	}

	@Override
	public List<LogHistory> findAllLogHistoryOrderByDateAndTime() {
		return logHistoryDao.findAllLogHistoryOrderByDateAndTime();
	}

	@Override
	public List<LogHistory> findAllLogHistoryOrderByDateAndTimeJoinCompanyParameterAndCompany() {
		return logHistoryDao.findAllLogHistoryOrderByDateAndTimeJoinCompanyParameterAndCompany();
	}

	@Override
	public Long findSummaryLogHistorySuccess(String logHistoryStatus) {
		return logHistoryDao.findSummaryLogHistorySuccess(logHistoryStatus);
	}

	@Override
	public List<LogHistory> findAllLogHistoryByStatusOrderByDateAndTime(String logHistoryStatus) {
		return logHistoryDao.findAllLogHistoryByStatusOrderByDateAndTime(logHistoryStatus);
	}

}
