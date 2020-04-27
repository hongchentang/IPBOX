package com.hcis.ipr.intellectual.cost.service;


import javax.mail.Session;
import javax.mail.internet.MimeMessage;

/**
 * @author zhw
 */
public interface MailService {

	/**
	 * 创建邮件
	 * @param session
	 * @param myEmailAccount
	 * @param myEmailName
	 * @param receiveMailAccount
	 * @param receiveMailName
	 * @param mailSubject
	 * @param mailContext
	 * @return
	 */
	MimeMessage createMimeMessage(Session session,String myEmailAccount,String myEmailName,String receiveMailAccount,String receiveMailName,String mailSubject,String mailContext) throws Exception;
}
