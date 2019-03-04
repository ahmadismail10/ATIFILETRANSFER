package com.anabatic.atifiletransfer.helper;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.Callable;

import org.apache.commons.net.ftp.FTPFile;

import com.anabatic.atifiletransfer.entities.Company;
import com.anabatic.atifiletransfer.entities.CompanyParameter;
import com.anabatic.atifiletransfer.entities.CompanyParameter.ProtocolCompany;
import com.anabatic.atifiletransfer.service.LogHistoryService;

public class TaskSFTPPut implements Callable<String> {

	LogHistoryService logHistorySvc;

	Company company;

	DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyy");

	public TaskSFTPPut(Company company, LogHistoryService logHistorySvc) {
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
				SFTPStatus sftpStatus = ftpBatch.putFile(companyParameter.getCompanyParameterIp(), companyParameter.getCompanyParameterPort(), companyParameter.getCompanyParameterUsername(), companyParameter.getCompanyParameterPassword(), companyParameter.getCompanyParameterPrefix(), companyParameter.getCompanyParameterFileExtension(), directory + "/fromT24", companyParameter.getCompanyParameterRemoteDirectory()+"/", companyParameter.getCompanyParameterId());
				logHistorySvc.saveLogHistories(sftpStatus.getListHistories());
				if (sftpStatus.getStatus()) {
					FileAction.fileMoverHistories(sftpStatus.getListHistories(), directory, directory + "/backup/"+ companyParameter.getCompanyParameterType() + "/" + dateFormat.format(new Date()),"");
					FTPFile[] ftpFiles = ftpBatch.getFileListDir(companyParameter.getCompanyParameterRemoteDirectory()+"/");
					if(ftpBatch.moveFileFTPBatch(companyParameter.getCompanyParameterRemoteDirectory()+"/"+companyParameter.getCompany().getCompanyName()+"/IN/temp", companyParameter.getCompanyParameterRemoteDirectory()+"/"+companyParameter.getCompany().getCompanyName()+"/IN", ftpFiles, ".temp")) {
						ftpBatch.moveFileFTPBatch(companyParameter.getCompanyParameterRemoteDirectory()+"/"+companyParameter.getCompany().getCompanyName()+"/IN", companyParameter.getCompanyParameterRemoteDirectory()+"/"+companyParameter.getCompany().getCompanyName()+"/IN", ftpBatch.getFileListDir(companyParameter.getCompanyParameterRemoteDirectory()+"/"+companyParameter.getCompany().getCompanyName()+"/IN"), "");
					}
				}			
				ftpBatch.closeConnection();	
			} else {
				SFTPbatch sftpBatch = new SFTPbatch();
				SFTPStatus sftpStatus = sftpBatch.putFile(
						companyParameter.getCompany().getCoreSystem().getCoreSystemLocalDirectory() + "/"
								+ companyParameter.getCompany().getCompanyName(),
						companyParameter.getCompanyParameterRemoteDirectory(), companyParameter.getCompanyParameterIp(),
						companyParameter.getCompanyParameterUsername(), companyParameter.getCompanyParameterPassword(),
						companyParameter.getCompanyParameterPrefix(), companyParameter.getCompanyParameterFileExtension(),
						companyParameter.getCompanyParameterPort(), companyParameter.getCompanyParameterId());
				logHistorySvc.saveLogHistories(sftpStatus.getListHistories());
				if (sftpStatus.getStatus()) {
					sftpBatch.deleteFileSFTPBatch(companyParameter.getCompany().getCoreSystem().getCoreSystemLocalDirectory()+"/"+companyParameter.getCompany().getCompanyName() + "/fromT24", sftpStatus.getListHistories());
					if (FileAction.fileMoverHistories(
							sftpStatus.getListHistories(), directory + "/fromT24", directory + "/fromT24/backup/"
									+ companyParameter.getCompanyParameterType() + "/" + dateFormat.format(new Date()),
							"")) {
						sftpBatch.moveFileSFTPBatch(companyParameter.getCompanyParameterRemoteDirectory() + "/temp",
								companyParameter.getCompanyParameterRemoteDirectory(),
								sftpBatch.getFileListDir(companyParameter.getCompanyParameterRemoteDirectory() + "/temp"),
								".temp");
						sftpBatch.moveFileSFTPBatch(companyParameter.getCompanyParameterRemoteDirectory(),
								companyParameter.getCompanyParameterRemoteDirectory(),
								sftpBatch.getFileListDir(companyParameter.getCompanyParameterRemoteDirectory()), "");
					}
				}
				sftpBatch.closeConnection();
			}
		}
	}
	
}
