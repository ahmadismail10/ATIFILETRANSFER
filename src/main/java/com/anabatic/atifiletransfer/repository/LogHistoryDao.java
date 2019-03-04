package com.anabatic.atifiletransfer.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.anabatic.atifiletransfer.entities.LogHistory;

public interface LogHistoryDao extends JpaRepository<LogHistory, Long> {
	
	@Query("from LogHistory order by logHistoryDate, logHistoryTime desc ")
	public List<LogHistory> findAllLogHistoryOrderByDateAndTime();
	
	@Query("from LogHistory where logHistoryStatus = :logHistoryStatus order by logHistoryDate, logHistoryTime desc ")
	public List<LogHistory> findAllLogHistoryByStatusOrderByDateAndTime(@Param(value = "logHistoryStatus") String logHistoryStatus);
	
	@Query("select count(logHistoryId) from LogHistory where logHistoryStatus = :logHistoryStatus")
	public Long findSummaryLogHistorySuccess(@Param(value = "logHistoryStatus") String logHistoryStatus);
	
	@Query("select l from LogHistory l join l.companyParameter p join p.company c order by l.logHistoryDate, l.logHistoryTime desc")
	public List<LogHistory> findAllLogHistoryOrderByDateAndTimeJoinCompanyParameterAndCompany();
}
