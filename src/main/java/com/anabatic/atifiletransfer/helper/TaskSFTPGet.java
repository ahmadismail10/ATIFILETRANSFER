package com.anabatic.atifiletransfer.helper;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.Callable;

import org.apache.log4j.Logger;

import com.anabatic.atifiletransfer.entities.Company;
import com.anabatic.atifiletransfer.entities.CompanyParameter;
import com.anabatic.atifiletransfer.entities.CompanyParameter.ProtocolCompany;
import com.anabatic.atifiletransfer.service.LogHistoryService;

public class TaskSFTPGet implements Callable<String> {
	
	private static final Logger logger = Logger.getLogger(TaskSFTPGet.class);

	LogHistoryService logHistorySvc;

	Company company;

	DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyy");

	public TaskSFTPGet(Company company, LogHistoryService logHistorySvc) {
		this.company = company;
		this.logHistorySvc = logHistorySvc;
	}

	@Override
	public String call() throws Exception {
		process();
		String message = String.format("Company name : " + company.getCompanyName() + " is done");
		return message;
	}

	private void process() throws ParseException {
		for (CompanyParameter companyParameter : company.getCompaniesParameter()) {
			String directory = companyParameter.getCompany().getCoreSystem().getCoreSystemLocalDirectory() + "/"
					+ companyParameter.getCompany().getCompanyName();
			if(companyParameter.getCompanyParameterProtocol().equals(ProtocolCompany.FTP)) {
				FTPbatch ftpBatch = new FTPbatch();
				SFTPStatus sftpStatus = ftpBatch.getFile(companyParameter.getCompanyParameterIp(), companyParameter.getCompanyParameterPort(), companyParameter.getCompanyParameterUsername(), companyParameter.getCompanyParameterPassword(), companyParameter.getCompanyParameterPrefix(), companyParameter.getCompanyParameterFileExtension(), directory ,companyParameter.getCompanyParameterRemoteDirectory(), companyParameter.getCompanyParameterId());
				logHistorySvc.saveLogHistories(sftpStatus.getListHistories());
				if (sftpStatus.getStatus()) {
					if (FileAction.fileCopyHistories(sftpStatus.getListHistories(), directory+"/temp", directory + "/backup/"+ companyParameter.getCompanyParameterType() + "/" + dateFormat.format(new Date()))) {
						FileAction.fileMoverAll(FileList.getFileNameLocal(directory+"/temp"), directory+"/temp", directory, ".temp");
						FileAction.fileRename(FileList.getFileNameLocal(directory), directory, ".temp");
					}
				}
				ftpBatch.closeConnection();
			} else {
				SFTPbatch sftpBatch = new SFTPbatch();
				SFTPStatus sftpStatus = sftpBatch.getFile(companyParameter.getCompanyParameterRemoteDirectory(),
						directory,
						companyParameter.getCompanyParameterIp(), companyParameter.getCompanyParameterUsername(),
						companyParameter.getCompanyParameterPassword(), companyParameter.getCompanyParameterPrefix(),
						companyParameter.getCompanyParameterFileExtension(), companyParameter.getCompanyParameterPort(),
						"toT24", companyParameter.getCompanyParameterId());
				logger.debug(companyParameter.getCompanyParameterRemoteDirectory() + " " +
						directory + " " +
						companyParameter.getCompanyParameterIp() + " " + companyParameter.getCompanyParameterUsername() + " " +
						companyParameter.getCompanyParameterPassword() + " " + companyParameter.getCompanyParameterPrefix() + " " +
						companyParameter.getCompanyParameterFileExtension() + " " + companyParameter.getCompanyParameterPort() + " " +
						"toT24" + " " + companyParameter.getCompanyParameterId());
				logHistorySvc.saveLogHistories(sftpStatus.getListHistories());
				if (sftpStatus.getStatus()) {
					sftpBatch.deleteFileSFTPBatch(companyParameter.getCompanyParameterRemoteDirectory(), sftpStatus.getListHistories());
					if (FileAction.fileCopy(directory + "/toT24/temp/", directory + "/toT24/backup/"
							+ companyParameter.getCompanyParameterType() + "/" + dateFormat.format(new Date()))) {
						if (FileAction.fileMoverAll(FileList.getFileNameLocal(directory + "/toT24/temp"),
								directory + "/toT24/temp", directory + "/toT24", ".temp")) {
							FileAction.fileRename(
									FileList.getFileNameLocal(directory + "/toT24", new FileFilter(".temp", "")),
									directory + "/toT24", ".temp");
						}
					}
				}
				sftpBatch.closeConnection();
			}
		}
	}
	
}
