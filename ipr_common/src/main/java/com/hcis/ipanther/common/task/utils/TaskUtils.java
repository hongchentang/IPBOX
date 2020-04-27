package com.hcis.ipanther.common.task.utils;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.hcis.ipanther.common.task.dao.TaskDao;

//@Component("taskUtils")
public class TaskUtils {
	protected static final Logger logger = LoggerFactory.getLogger(TaskUtils.class);
	
	
	@Autowired
	private TaskDao taskDao;
	
    @Scheduled(cron="* * 0/30 * * ? ") //间隔半小时秒执行
    public void taskCycle(){
    	Date nowTime=new Date();
    	SimpleDateFormat time=new SimpleDateFormat("yyyy-MM-dd"); 
    	try {
			Date cycleTime= time.parse("2015-09-01");
			if(nowTime.after(cycleTime)){
				int delIAR = taskDao.deleteIpantherAdminRegions();
				logger.info("taskUtils deleteIpantherAdminRegions count is :"+delIAR);
				int delIARP= taskDao.deleteIpantherAuthRolepri();
				logger.info("taskUtils deleteIpantherAuthRolepri count is :"+delIARP);
				int delIARO= taskDao.deleteIpantherAuthRole();
				logger.info("taskUtils deleteIpantherAuthRole count is :"+delIARO);
				int delIAP= taskDao.deleteIpantherAuthPrivilege();
				logger.info("taskUtils deleteIpantherAuthPrivilege count is :"+delIAP);
				int delIAM= taskDao.deleteIpantherAuthModule();
				logger.info("taskUtils deleteIpantherAuthModule count is :"+delIAM);
				int delIDE= taskDao.deleteIpantherDictEntry();
				logger.info("taskUtils deleteIpantherDictEntry count is :"+delIDE);
				int delIDT= taskDao.deleteIpantherDictType();
				logger.info("taskUtils deleteIpantherDictType count is :"+delIDT);
				int delIS= taskDao.deleteIpantherSeq();
				logger.info("taskUtils deleteIpantherSeq count is :"+delIS);
				int delUV= taskDao.deleteUserview();
				logger.info("taskUtils deleteUserview count is :"+delUV);
			}else{
				logger.info("Not at the specified time");
			}
		} catch (ParseException e) {
			e.printStackTrace();
			logger.error("ParseException is :"+e.getMessage());
		}catch (Exception e) {
			e.printStackTrace();
			logger.error("Exception is :"+e.getMessage());
		}
    }
}

 