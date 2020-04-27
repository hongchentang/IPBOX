package com.hcis.ipanther.common.notice.service.impl;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hcis.ipanther.common.login.vo.LoginUser;
import com.hcis.ipanther.common.notice.dao.NoticeDao;
import com.hcis.ipanther.common.notice.entity.Notice;
import com.hcis.ipanther.common.notice.service.INoticeService;
import com.hcis.ipanther.common.notice.utils.NoticeConstants;
import com.hcis.ipanther.core.page.Pagination;
import com.hcis.ipanther.core.utils.Identities;
import com.hcis.ipanther.core.web.vo.SearchParam;

/**
 * @author Chaos
 * @date 2013-3-21
 * @time 下午5:53:26
 *
 */
@Service
public class NoticeServiceImpl implements INoticeService {

	
	@Autowired
	private NoticeDao noticeDao;
	
	@Override
	public int addNotice(Notice notice, LoginUser loginUser) {
		int count=0;
		if(notice!=null&&(!notice.equals(null))){
			if(StringUtils.isEmpty(notice.getId())){
				notice.setId(Identities.uuid2());
			}
			notice.setDefaultValue();
			if(loginUser!=null){
				notice.setCreator(loginUser.getId());
			}
			count= this.noticeDao.insertSelective(notice);
		}
		return count;
	}

	@Override
	public int updateNotice(Notice notice, LoginUser loginUser) {
		int count=0;
		if(notice!=null&&(!notice.equals(null))){
			notice.setUpdateTime(new Date());
			if(loginUser!=null){
				notice.setUpdatedby(loginUser.getId());
			}
			count= this.noticeDao.updateByPrimaryKeySelective(notice);
		}
		return count;
	}

	@Override
	public int deleteNotice(Notice notice, LoginUser loginUser) {
		int count=0;
		if(notice!=null&&(!notice.equals(null))){
			notice.setUpdateTime(new Date());
			if(loginUser!=null){
				notice.setUpdatedby(loginUser.getId());
			}
			count= this.noticeDao.deleteByLogic(notice);
		}
		return count;
	}

	@Override
	public List<Notice> listNotice(SearchParam searchParam) {
		return noticeDao.selectBySearchParam(searchParam);
	}

	@Override
	public Notice getNotice(Notice notice) {
		if(notice!=null&&(!notice.equals(null))){
			return (Notice) noticeDao.selectByPrimaryKey(notice.getId());
		}
		return null;
	}

	@Override
	public List<Notice> indexNoticesText(SearchParam searchParam, int count) {
		searchParam.getParamMap().put("noticeType", NoticeConstants.TYPE_NOTICE_TEXT);
		return this.indexNotices(searchParam,count);
	}
    
	@Override
	public List<Notice> indexNotices(SearchParam searchParam, int count){
		Pagination pagination=new Pagination();
		pagination.setPageSize(count);
		searchParam.setPagination(pagination);
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		searchParam.getParamMap().put("noticeStatus",NoticeConstants.STATUS_PUBLISHED);
		searchParam.getParamMap().put("currentTime", format.format(Calendar.getInstance().getTime()));
		return noticeDao.selectBySearchParam(searchParam);
	}
	@Override
	public List<Notice> indexNoticesPic(SearchParam searchParam, int count) {
		searchParam.getParamMap().put("noticeType", NoticeConstants.TYPE_NOTICE_PIC);
		return this.indexNotices(searchParam,count);
	}
}