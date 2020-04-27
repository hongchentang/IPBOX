package com.hcis.ipr.intellectual.patent.dao;

import com.hcis.ipanther.core.persistence.dao.MyBatisDao;
import com.hcis.ipanther.core.web.vo.SearchParam;
import com.hcis.ipr.intellectual.patent.entity.PatentAttachment;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("patentAttachmentDao")
public class PatentAttachmentDao extends MyBatisDao {

    /**
     * 批量插入专利附件
     * @param list xx
     */
    public void insertBatch(List<PatentAttachment> list) {
        this.getSqlSession().insert(namespace + ".insertBatch", list);
    }

    /**
     * 根据专利的申请号来获取附件
     * @param patentAppNumber
     * @return
     */
    public List<PatentAttachment> getPatentAttachments(String patentAppNumber){
        return this.getSqlSession().selectList(namespace + ".getPatentAttachments", patentAppNumber);
    }

    /**
     *  删除上报的文件并且返回附件表的id
     * @param searchParam
     * @return
     */
    public List<String> deleteByAppNumberAndTypes(SearchParam searchParam) {
        this.getSqlSession().update(namespace + ".update", searchParam);
        //return this.getSqlSession().selectList(namespace + ".selectIdsByAppNumberAndTypes", searchParam);

        return null;
    }

    public void deleteByIds(String[] attachmentIds) {
        this.getSqlSession().update(namespace + ".deleteByIds", attachmentIds);
    }

    public List<String> selectAllFile(String id) {
        return this.getSqlSession().selectList(namespace + ".selectAllFile", id);
    }
}
