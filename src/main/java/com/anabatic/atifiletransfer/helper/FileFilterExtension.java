package com.anabatic.atifiletransfer.helper;

import java.io.File;
import java.io.FilenameFilter;

public class FileFilterExtension implements FilenameFilter {
	
	private String ext;
	
	private String prefix;
	
	public FileFilterExtension(String ext, String prefix) {
		this.ext = ext;
		this.prefix = prefix;
	}

	@Override
	public boolean accept(File directory, String fileName) {
		return  fileName.contains(prefix) && fileName.contains(ext);
	}

}
