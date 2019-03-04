package com.anabatic.atifiletransfer.component;

import java.text.ParseException;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.stereotype.Component;

import com.anabatic.atifiletransfer.entities.Company;
import com.anabatic.atifiletransfer.entities.Company.StatusCompany;
import com.anabatic.atifiletransfer.helper.TaskSFTPGet;
import com.anabatic.atifiletransfer.helper.TaskSFTPPut;
import com.anabatic.atifiletransfer.service.CompanyService;
import com.anabatic.atifiletransfer.service.CoreSystemService;
import com.anabatic.atifiletransfer.service.LogHistoryService;

@Component
public class SchedulledSFTP {

	private static final Logger logger = Logger.getLogger(SchedulledSFTP.class);
	
	@Autowired
	CoreSystemService coreSysSvc;
	
	@Autowired
	CompanyService companySvc;

	@Autowired
	LogHistoryService logHistorySvc;
	
	@Autowired
	ThreadPoolTaskExecutor threadPool;
	
	@Scheduled(fixedDelayString = "${property.fixed.delay.seconds:2}000")
	public void getFileSFTPInsurance() throws ParseException {
		List<Company> companies = companySvc.findAllCompanyJoinParameterByParameterTypeAndCompanyStatusAndCompanyType(StatusCompany.Active,"Get");
		for (Company company : companies) {
			TaskSFTPGet callableTask = new TaskSFTPGet(company,logHistorySvc);
			threadPool.submit(callableTask);
			logger.debug(company.getCompanyName());
		}
		try {
			Thread.sleep(coreSysSvc.findOneCoreSystem("SYSTEM").getCoreSystemTimer()*60000);
		} catch (Exception ex) {

		}
	}
	
	@Scheduled(fixedDelayString = "${property.fixed.delay.seconds:2}000")
	public void putFileSFTPInsurance() throws ParseException {
		List<Company> companies = companySvc.findAllCompanyJoinParameterByParameterTypeAndCompanyStatusAndCompanyType(StatusCompany.Active,"Put");
		for (Company company : companies) {
			TaskSFTPPut callableTask = new TaskSFTPPut(company,logHistorySvc);
			threadPool.submit(callableTask);
		}
		try {
			Thread.sleep(coreSysSvc.findOneCoreSystem("SYSTEM").getCoreSystemTimer()*60000);
		} catch (Exception ex) {

		}
	}
	
}
