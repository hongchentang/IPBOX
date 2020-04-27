package com.hcis.ipanther.common.file.dao;

import org.springframework.stereotype.Repository;


import com.hcis.ipanther.common.file.entity.FilesUnit;
import com.hcis.ipanther.core.persistence.dao.MyBatisDao;

@Repository("FilesDao")
public class FilesDao extends MyBatisDao {
	
	public FilesUnit getFirstFilesPath() {
		FilesUnit filesUnit = new FilesUnit();
		return (FilesUnit) this.selectOne(filesUnit);
	}
}

