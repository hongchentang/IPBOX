/**
 * 
 */
package com.hcis.ipanther.common.notice.service;

import java.util.List;

import com.hcis.ipanther.common.login.vo.LoginUser;
import com.hcis.ipanther.common.notice.entity.Notice;
import com.hcis.ipanther.core.web.vo.SearchParam;

/**
 * @author Chaos
 * @date 2013-3-21
 * @time 下午5:52:01
 *
 */
public interface INoticeService {

	/**
	 * @param notice
	 * @return
	 */
	public int addNotice(Notice notice,LoginUser loginUser);

	/**
	 * @param notice
	 * @return
	 */
	public int updateNotice(Notice notice,LoginUser loginUser);

	/**
	 * @param notice
	 * @return
	 */
	public int deleteNotice(Notice notice,LoginUser loginUser);

	/**
	 * List notice.
	 *
	 * @param searchParam the search param
	 * @return the list
	 */
	public List<Notice> listNotice(SearchParam searchParam);

	/**
	 * Gets the notice.
	 *
	 * @param notice the notice
	 * @return the notice
	 */
	public Notice getNotice(Notice notice);

	/**
	 * Index notices text.
	 *
	 * @param searchParam the search param
	 * @param count the count
	 * @return the list
	 */
	public List<Notice> indexNoticesText(SearchParam searchParam, int count);

	/**
	 * Index notices pic.
	 *
	 * @param searchParam the search param
	 * @param count the count
	 * @return the list
	 */
	public List<Notice> indexNoticesPic(SearchParam searchParam, int count);
	
	/**
	 * Index notices.
	 *
	 * @param searchParam the search param
	 * @param count the count
	 * @return the list
	 */
	public List<Notice> indexNotices(SearchParam searchParam, int count);
}
