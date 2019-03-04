package com.anabatic.atifiletransfer.helper;

import org.apache.commons.net.ftp.FTPFile;
import org.apache.commons.net.ftp.FTPFileFilter;

public class FTPFilter implements FTPFileFilter{

	private String prefix;
	
	private String fileExtension;
	
	public FTPFilter(String prefix, String fileExtension) {
		this.prefix = prefix;
		this.fileExtension = fileExtension;
	}
	
	@Override
	public boolean accept(FTPFile ftpFile) {
		 return (ftpFile.isFile() && ftpFile.getName().endsWith(fileExtension) && ftpFile.getName().contains(prefix));
	}

}
