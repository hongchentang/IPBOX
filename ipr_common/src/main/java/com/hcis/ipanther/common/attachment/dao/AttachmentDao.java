package com.hcis.ipanther.common.attachment.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.hcis.ipanther.common.attachment.entity.Attachment;
import com.hcis.ipanther.core.persistence.dao.MyBatisDao;
/**
 * 
 * 
 * 更想版本是
 * @author Administrator
 *
 */
@Repository("attachmentDao")
public class AttachmentDao extends MyBatisDao {
	
	public List<Attachment> selectInvalidAttachment(){
		return this.selectForList(namespace+".selectInvalidAttachment");
	}
	
	public int updateValid(Attachment attachment){
		return this.update(namespace+".updateValid", attachment);
	}
	
	public int updateTempFile(Attachment attachment){
		return this.update(namespace+".updateTempFile", attachment);
	}
	
	public List<Attachment> selectByFileDirectory(String fileDirecotry){
		return this.selectForList(namespace+".selectByFileDirectory", fileDirecotry);
	}
}
