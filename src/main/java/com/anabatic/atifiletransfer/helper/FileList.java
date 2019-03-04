package com.anabatic.atifiletransfer.helper;

import java.io.File;

public class FileList {
	
	public static File[] getFileNameLocal(String directory) {
		File folder = new File(directory);
		File[] listOfFiles = folder.listFiles();
		return listOfFiles;
	}
	
	public static File[] getFileNameLocal(String directory,FileFilter fileFilter) {
		File folder = new File(directory);
		File[] listOfFiles = folder.listFiles(fileFilter);
		return listOfFiles;
	}
	
	public static void createDirectory(String directory) {
		File f = new File(directory);
		if (!f.exists()) {
			f.mkdirs();
		}
	}
	
	public static File[] getFileNameLocalExtension(String directory,FileFilterExtension fileFilter) {
		File folder = new File(directory);
		File[] listOfFiles = folder.listFiles(fileFilter);
		return listOfFiles;
	}

}
