package com.hcis.ipanther.common.file.service;


import com.hcis.ipanther.common.file.entity.FilesUnit;
import com.hcis.ipanther.core.service.IBaseService;


public interface IFilesService extends IBaseService<FilesUnit>{

	
	public FilesUnit getFirstFilePath();
	
}
