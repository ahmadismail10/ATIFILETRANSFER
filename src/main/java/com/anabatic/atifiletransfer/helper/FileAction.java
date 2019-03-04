package com.anabatic.atifiletransfer.helper;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.CopyOption;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

import org.apache.commons.net.ftp.FTPClient;

import com.anabatic.atifiletransfer.entities.LogHistory;

public class FileAction {
	
	public static CopyOption[] options = new CopyOption[] {StandardCopyOption.REPLACE_EXISTING};

	DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyy");
	DateFormat timeFormat = new SimpleDateFormat("HH:mm:ss");

	public static Boolean fileCopy(String source, String destination) throws ParseException {
		File[] listOfFiles = FileList.getFileNameLocal(source);
		try {
			FileList.createDirectory(destination);
			for (int i = 0; i < listOfFiles.length; i++) {
				if (listOfFiles[i].isFile()) {
					Files.copy(Paths.get(source + "/" + listOfFiles[i].getName()),
							Paths.get(destination + "/" + listOfFiles[i].getName()));
				}
			}
			return true;
		} catch (Exception ex) {
			System.out.println(ex);
			return false;
		}
	}

	public static Boolean fileMoverAll(File[] listOfFiles, String source, String destination, String extension) {
		try {
			FileList.createDirectory(destination);
			for (int i = 0; i < listOfFiles.length; i++) {
				if (listOfFiles[i].isFile()) {
					Files.move(Paths.get(source + "/" + listOfFiles[i].getName()),
							Paths.get(destination + "/" + listOfFiles[i].getName() + extension));
				}
			}
			return true;
		} catch (Exception ex) {
			System.out.println(ex);
			return false;
		}
	}

	public static Boolean fileCopy(String fileName, String source, String destination, String extension) {
		try {
			FileList.createDirectory(destination);
			Files.copy(Paths.get(source + "/" + fileName),
					Paths.get(destination + "/" + fileName));
			return true;
		} catch (Exception ex) {
			System.out.println(ex);
			return false;
		}
	}
	
	public static Boolean fileCopyHistories(List<LogHistory> logHistories, String source, String destination) {
		try {
			for(LogHistory logHistory : logHistories) {
				FileList.createDirectory(destination);
				Files.copy(Paths.get(source + "/" + logHistory.getLogHistoryFileName()),
						Paths.get(destination + "/" + logHistory.getLogHistoryFileName()));
			}
			return true;
		} catch (Exception ex) {
			System.out.println(ex);
			return false;
		}
	}
	
	public static Boolean deleteFile(String source, String fileName) {
		try {
			Files.delete(Paths.get(source + "/" + fileName));
			return true;
		} catch (Exception ex) {
			System.out.println(ex);
			return false;
		}
	}

	public static Boolean fileMoverHistories(List<LogHistory> logHistories, String source, String destination,
			String extension) {
		try {
			FileList.createDirectory(destination);
			for (LogHistory logHistory : logHistories) {
				Files.move(Paths.get(source + "/" + logHistory.getLogHistoryFileName()),
						Paths.get(destination + "/" + logHistory.getLogHistoryFileName() + extension), options);
			}
			return true;
		} catch (Exception ex) {
			System.out.println(ex);
			return false;
		}
	}

	public static Boolean fileRename(File[] listOfFiles, String directory, String extensionFilter) {
		try {
			for (int i = 0; i < listOfFiles.length; i++) {
				if (listOfFiles[i].isFile()) {
					File oldName = new File(directory + "/" + listOfFiles[i].getName());
					oldName.renameTo(new File(directory + "/" + listOfFiles[i].getName().replace(extensionFilter, "")));
				}
			}
			return true;
		} catch (Exception ex) {
			System.out.println(ex);
			return false;
		}
	}
	
	public static boolean makeDirectories(FTPClient ftpClient, String dirPath)
            throws IOException {
        String[] pathElements = dirPath.split("/");
        if (pathElements != null && pathElements.length > 0) {
            for (String singleDir : pathElements) {
                boolean existed = ftpClient.changeWorkingDirectory(singleDir);
                if (!existed) {
                    boolean created = ftpClient.makeDirectory(singleDir);
                    if (created) {
                        System.out.println("CREATED directory: " + singleDir);
                        ftpClient.changeWorkingDirectory(singleDir);
                    } else {
                        System.out.println("COULD NOT create directory: " + singleDir);
                        return false;
                    }
                }
            }
        }
        return true;
    }

}
