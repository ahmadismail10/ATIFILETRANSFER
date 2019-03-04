package com.anabatic.atifiletransfer.helper;

import java.io.File;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Vector;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import com.anabatic.atifiletransfer.controller.CompanyController;
import com.anabatic.atifiletransfer.entities.CompanyParameter;
import com.anabatic.atifiletransfer.entities.LogHistory;
import com.jcraft.jsch.Channel;
import com.jcraft.jsch.ChannelSftp;
import com.jcraft.jsch.JSch;
import com.jcraft.jsch.Session;
import com.jcraft.jsch.SftpException;
import com.jcraft.jsch.ChannelSftp.LsEntry;

public class SFTPbatch {
	
	private static final Logger logger = Logger.getLogger(CompanyController.class);

	DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyy");
	DateFormat timeFormat = new SimpleDateFormat("HH:mm:ss");

	@Autowired
	Session session;

	@Autowired
	Channel channel;

	@Autowired
	ChannelSftp channelSftp;

	@Autowired
	Vector<LsEntry> filelist;

	@Autowired
	File[] files;

	public void closeConnection() {
		channelSftp.exit();
		channel.disconnect();
		session.disconnect();
	}

	@SuppressWarnings("unchecked")
	public List<String> getFileListDir(String directory) {
		List<String> list = new ArrayList<>();
		Vector<LsEntry> files;
		try {
			files = channelSftp.ls(directory);
			for (LsEntry entry : files) {
				if (!entry.getFilename().equals(".") && !entry.getFilename().equals("..")) {
					list.add(entry.getFilename());
				}
			}
		} catch (SftpException e) {
			logger.error("Get List Dir SFTP", e);
			return list;
		}
		return list;
	}	
	
	public void createDirectorySftp(String destinationDir) throws SftpException {
		String[] folders = destinationDir.split( "/" );
		String path = "";
		for ( String folder : folders ) {
			path = path + folder + "/";
			try {
				channelSftp.ls(path);
			} catch (SftpException e) {
				if (e.id == ChannelSftp.SSH_FX_NO_SUCH_FILE) {
					channelSftp.mkdir(path);
				}
			}
		}
	}
	
	public Boolean moveFileSFTPBatch(String sourceDirFile, String destinationDirFile, List<String> list, String fileExtensionRename) {
		try {
			for(String file : list) {
				channelSftp.rename(sourceDirFile+"/"+file, destinationDirFile+"/"+file.replace(".temp", "")+fileExtensionRename);	
			}
		} catch (SftpException e) {
			logger.error("Move File SFTP", e);
			return false;
		}
		return true;
	}

	public Boolean moveFileSFTP(String sourceDirFile, String destinationDirFile) {
		try {
			channelSftp.rename(sourceDirFile, destinationDirFile);
		} catch (SftpException e) {
			logger.error("Move File SFTP", e);
			return false;
		}
		return true;
	}
	
	public Boolean deleteFileSFTPBatch(String sourceDirFile, List<LogHistory> list) {
		try {
			for(LogHistory file : list) {
				channelSftp.rm(sourceDirFile + "/" + file.getLogHistoryFileName());
			}
		} catch (SftpException e) {
			logger.error("Delete File SFTP", e);
			return false;
		}
		return true;
	}

	@SuppressWarnings("unchecked")
	public SFTPStatus getFile(String sourceDir, String destinationDir, String ip, String username, String password,
			String prefix, String fileExtension, int port, String folder, Long companyParameterId)
			throws ParseException {
		List<LogHistory> logHistoriesList = new ArrayList<LogHistory>();
		try {
			JSch jsch = new JSch();
			session = jsch.getSession(username, ip, port);
			session.setPassword(password);
			java.util.Properties config = new java.util.Properties();
			config.put("StrictHostKeyChecking", "no");
			session.setConfig(config);
			session.setTimeout(40000);
			session.connect();
			channel = session.openChannel("sftp");
			channel.connect();
			channelSftp = (ChannelSftp) channel;
			File f = new File(destinationDir + "/" + folder + "/temp");
			if (!f.exists()) {
				f.mkdirs();
			}
			filelist = channelSftp.ls(sourceDir + "/*" + prefix + fileExtension);
			if(!filelist.isEmpty()){
				channelSftp.get(sourceDir + "/*" + prefix + fileExtension, destinationDir + "/" + folder + "/");
			}
			
			for (LsEntry file : filelist) {
				logHistoriesList.add(new LogHistory(file.getFilename(), file.getAttrs().getSize(),
						dateFormat.parse(dateFormat.format(new Date())),
						timeFormat.parse(timeFormat.format(new Date())), "Download", "Success", "",
						new CompanyParameter(companyParameterId)));
			}
			return new SFTPStatus(logHistoriesList, true);
		} catch (Exception ex) {
			for (LsEntry file : filelist) {
				logHistoriesList.add(new LogHistory(file.getFilename(), file.getAttrs().getSize(),
						dateFormat.parse(dateFormat.format(new Date())),
						timeFormat.parse(timeFormat.format(new Date())), "Download", "Fail", ex.toString(),
						new CompanyParameter()));
			}
			logger.error("Get File SFTP", ex);
			return new SFTPStatus(logHistoriesList, false);
		}
	}

	@SuppressWarnings("unchecked")
	public SFTPStatus getFileWithKey(String sourceDir, String destinationDir, String ip, String username, String password,
			String prefix, String fileExtension, int port, String folder, Long companyParameterId, String privateKey)
			throws ParseException {
		List<LogHistory> logHistoriesList = new ArrayList<LogHistory>();
		try {
			JSch jsch = new JSch();
			jsch.addIdentity(privateKey);
			session = jsch.getSession(username, ip, port);
			session.setPassword(password);
			java.util.Properties config = new java.util.Properties();
			config.put("StrictHostKeyChecking", "no");
			session.setConfig(config);
			session.setTimeout(40000);
			session.connect();
			channel = session.openChannel("sftp");
			channel.connect();
			channelSftp = (ChannelSftp) channel;
			File f = new File(destinationDir + "/" + folder + "/temp");
			if (!f.exists()) {
				f.mkdirs();
			}
			filelist = channelSftp.ls(sourceDir + "/" + prefix + fileExtension);
			channelSftp.get(sourceDir + "/" + prefix + fileExtension, destinationDir + "/" + folder + "/temp");
			channelSftp.rm(sourceDir + "/" + prefix + fileExtension);
			for (LsEntry file : filelist) {
				logHistoriesList.add(new LogHistory(file.getFilename(), file.getAttrs().getSize(),
						dateFormat.parse(dateFormat.format(new Date())),
						timeFormat.parse(timeFormat.format(new Date())), "Download", "Success", "",
						new CompanyParameter(companyParameterId)));
			}
			return new SFTPStatus(logHistoriesList, true);
		} catch (Exception ex) {
			for (LsEntry file : filelist) {
				logHistoriesList.add(new LogHistory(file.getFilename(), file.getAttrs().getSize(),
						dateFormat.parse(dateFormat.format(new Date())),
						timeFormat.parse(timeFormat.format(new Date())), "Download", "Fail", ex.toString(),
						new CompanyParameter()));
			}
			logger.error("Get File SFTP", ex);
			return new SFTPStatus(logHistoriesList, false);
		}
	}

	public SFTPStatus putFile(String sourceDir, String destinationDir, String ip, String username, String password,
			String prefix, String fileExtension, int port, Long companyParameterId)
			throws ParseException {
		List<LogHistory> logHistoriesList = new ArrayList<LogHistory>();
		try {
			JSch jsch = new JSch();
			session = jsch.getSession(username, ip, port);
			session.setPassword(password);
			java.util.Properties config = new java.util.Properties();
			config.put("StrictHostKeyChecking", "no");
			session.setConfig(config);
			session.setTimeout(40000);
			session.connect();
			channel = session.openChannel("sftp");
			channel.connect();
			channelSftp = (ChannelSftp) channel;
			createDirectorySftp(destinationDir + "/temp");
			FileList.createDirectory(sourceDir);
			if(files.length > 0) {
				for (File file : files){
				channelSftp.put(sourceDir + "/" + file.getName(), destinationDir + "/" + file.getName());
				}
			}
			
			for (File file : files) {
				logHistoriesList.add(
						new LogHistory(file.getName(), file.length(), dateFormat.parse(dateFormat.format(new Date())),
								timeFormat.parse(timeFormat.format(new Date())), "Upload", "Success", "",
								new CompanyParameter(companyParameterId)));
			}
			return new SFTPStatus(logHistoriesList, true);
		} catch (Exception ex) {
			System.out.println(ex);
			for (File file : files) {
				logHistoriesList.add(
						new LogHistory(file.getName(), file.length(), dateFormat.parse(dateFormat.format(new Date())),
								timeFormat.parse(timeFormat.format(new Date())), "Upload", "Fail", ex.toString(),
								new CompanyParameter(companyParameterId)));
			}
			logger.error("Put File SFTP", ex);
			return new SFTPStatus(logHistoriesList, false);
		}
	}
	
	public SFTPStatus putFileWithKey(String sourceDir, String destinationDir, String ip, String username, String password,
			String prefix, String fileExtension, int port, String folder, Long companyParameterId, String privateKey)
			throws ParseException {
		List<LogHistory> logHistoriesList = new ArrayList<LogHistory>();
		try {
			JSch jsch = new JSch();
			jsch.addIdentity(privateKey);
			session = jsch.getSession(username, ip, port);
			session.setPassword(password);
			java.util.Properties config = new java.util.Properties();
			config.put("StrictHostKeyChecking", "no");
			session.setConfig(config);
			session.setTimeout(40000);
			session.connect();
			channel = session.openChannel("sftp");
			channel.connect();
			channelSftp = (ChannelSftp) channel;
			createDirectorySftp(destinationDir + "/temp");
			FileList.createDirectory(sourceDir + "/" + folder);
			files = FileList.getFileNameLocal(sourceDir + "/" + folder,
					new FileFilter(fileExtension.replace("*.", ""), prefix));
			channelSftp.put(sourceDir + "/" + folder + "/" + prefix + fileExtension, destinationDir + "/temp");
			for (File file : files) {
				logHistoriesList.add(
						new LogHistory(file.getName(), file.length(), dateFormat.parse(dateFormat.format(new Date())),
								timeFormat.parse(timeFormat.format(new Date())), "Download", "Success", "",
								new CompanyParameter(companyParameterId)));
			}
			return new SFTPStatus(logHistoriesList, true);
		} catch (Exception ex) {
			System.out.println(ex);
			for (File file : files) {
				logHistoriesList.add(
						new LogHistory(file.getName(), file.length(), dateFormat.parse(dateFormat.format(new Date())),
								timeFormat.parse(timeFormat.format(new Date())), "Download", "Fail", ex.toString(),
								new CompanyParameter(companyParameterId)));
			}
			logger.error("Put File SFTP", ex);
			return new SFTPStatus(logHistoriesList, false);
		}
	}

}
