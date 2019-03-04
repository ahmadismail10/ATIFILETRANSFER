package com.anabatic.atifiletransfer.helper;

import org.apache.commons.net.ftp.FTPFile;
import org.apache.commons.net.ftp.FTPFileFilter;

public class FTPFilterExtension implements FTPFileFilter{

	private String prefix;
	
	private String fileExtension;
	
	public FTPFilterExtension(String prefix, String fileExtension) {
		this.prefix = prefix;
		this.fileExtension = fileExtension;
	}
	
	@Override
	public boolean accept(FTPFile ftpFile) {
		 return (ftpFile.isFile() && ftpFile.getName().contains(fileExtension.replace("*", "")) && ftpFile.getName().contains(prefix));
	}

}
