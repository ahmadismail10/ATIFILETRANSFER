package com.anabatic.atifiletransfer.helper;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.apache.commons.net.ftp.FTP;
import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPFile;
import org.apache.log4j.Logger;

import com.anabatic.atifiletransfer.controller.CompanyController;
import com.anabatic.atifiletransfer.entities.CompanyParameter;
import com.anabatic.atifiletransfer.entities.LogHistory;

public class FTPbatch {
	
	private static final Logger logger = Logger.getLogger(CompanyController.class);

	DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyy");
	DateFormat timeFormat = new SimpleDateFormat("HH:mm:ss");

	FTPClient ftpClient = new FTPClient();

	FTPFile[] filesFTP;
	
	public void closeConnection() {
		try {
			if (ftpClient.isConnected()) {
				ftpClient.logout();
				ftpClient.disconnect();
			}
		} catch (IOException ex) {
			logger.error("Close Connection FTP", ex);
			ex.printStackTrace();
		}
	}

	public FTPFile[] getFileListDir(String directory) {
		FTPFile[] file;
		try {
			file = ftpClient.listFiles(directory);
		} catch (IOException e) {
			logger.error("Get List Dir FTP", e);
			return null;
		}
		return file;
	}	
	
	public Boolean moveFileFTPBatch(String sourceDirFile, String destinationDirFile, FTPFile[] files, String fileExtensionRename) {
		try {
			for(FTPFile file : files) {
				ftpClient.rename(sourceDirFile+"/"+file.getName(), destinationDirFile+"/"+file.getName().replace(".temp", "")+fileExtensionRename);	
			}
		} catch (Exception ex) {
			logger.error("Move File FTP", ex);
			return false;
		}
		return true;
	}
	
	public SFTPStatus putFile(String server, int port, String user, String pass, String prefix, String fileExtension, String directory, String remote, Long companyParameterId) throws ParseException {

		List<LogHistory> logHistoriesList = new ArrayList<LogHistory>();
		
		try {
			File[] files = FileList.getFileNameLocal(directory,new FileFilter(fileExtension.replace("*", ""), prefix));
			ftpClient.setConnectTimeout(40000);
			ftpClient.connect(server, port);
			ftpClient.login(user, pass);
			ftpClient.enterLocalPassiveMode();
			FileAction.makeDirectories(ftpClient, remote);
			ftpClient.setFileType(FTP.BINARY_FILE_TYPE);

			InputStream inputStream = null;

			ftpClient.changeWorkingDirectory(remote);
			if (ftpClient.getReplyCode() != 550) {
				String remoteTemp = remote+"/temp";
				ftpClient.makeDirectory(remoteTemp);
			}
			
			for(File file : files) {
				File firstLocalFile = new File(directory+"/"+file.getName());
				inputStream = new FileInputStream(firstLocalFile);
				String firstRemoteFile = remote+"/"+file.getName();
				
				if (ftpClient.storeFile(firstRemoteFile, inputStream)) {
					logHistoriesList.add(new LogHistory(file.getName(), file.length(),
							dateFormat.parse(dateFormat.format(new Date())),
							timeFormat.parse(timeFormat.format(new Date())), "Upload", "Success", "",
							new CompanyParameter(companyParameterId)));
					inputStream.close();
				} else {
					logHistoriesList.add(new LogHistory(file.getName(), file.length(),
							dateFormat.parse(dateFormat.format(new Date())),
							timeFormat.parse(timeFormat.format(new Date())), "Upload", "Fail",
							"Fail to download file", new CompanyParameter(companyParameterId)));
				}
			}
		} catch (Exception ex) {
			System.out.println(ex);
			for (FTPFile file : filesFTP) {
				logHistoriesList.add(
						new LogHistory(file.getName(), file.getSize(), dateFormat.parse(dateFormat.format(new Date())),
								timeFormat.parse(timeFormat.format(new Date())), "Download", "Fail",
								"Fail to download file", new CompanyParameter(companyParameterId)));
			}
			logger.error("Put File FTP", ex);
			return new SFTPStatus(logHistoriesList, false);
		} 
		return new SFTPStatus(logHistoriesList, true);
	}

	public SFTPStatus getFile(String server, int port, String user, String pass, String prefix, String fileExtension, String directory, String remote, Long companyParameterId) throws ParseException {
		List<LogHistory> logHistoriesList = new ArrayList<LogHistory>();
		try {
			ftpClient.setConnectTimeout(40000);
			ftpClient.connect(server, port);
			ftpClient.login(user, pass);
			ftpClient.enterLocalPassiveMode();
			ftpClient.setFileType(FTP.BINARY_FILE_TYPE);
			FileList.createDirectory(directory);
			OutputStream outputStream1 = null;
			for (FTPFile file : filesFTP) {
				String remoteFile1 = remote + "/" + file.getName();
				File downloadFile1 = new File(directory + "/" + file.getName());
				outputStream1 = new BufferedOutputStream(new FileOutputStream(downloadFile1));
				if (ftpClient.retrieveFile(remoteFile1, outputStream1)) {
					logHistoriesList.add(new LogHistory(file.getName(), file.getSize(),
							dateFormat.parse(dateFormat.format(new Date())),
							timeFormat.parse(timeFormat.format(new Date())), "Download", "Success", "",
							new CompanyParameter(companyParameterId)));
					ftpClient.deleteFile(remote+"/"+file.getName());
					outputStream1.close();
				} else {
					logHistoriesList.add(new LogHistory(file.getName(), file.getSize(),
							dateFormat.parse(dateFormat.format(new Date())),
							timeFormat.parse(timeFormat.format(new Date())), "Download", "Fail",
							"Fail to download file", new CompanyParameter(companyParameterId)));
				}

			}
		} catch (Exception ex) {
			System.out.println("Error: " + ex.getMessage());
			for (FTPFile file : filesFTP) {
				logHistoriesList.add(
						new LogHistory(file.getName(), file.getSize(), dateFormat.parse(dateFormat.format(new Date())),
								timeFormat.parse(timeFormat.format(new Date())), "Download", "Fail",
								"Fail to download file", new CompanyParameter(companyParameterId)));
			}
			logger.error("Get File FTP", ex);
			return new SFTPStatus(logHistoriesList, false);
		} 
		return new SFTPStatus(logHistoriesList, true);
	}

}
