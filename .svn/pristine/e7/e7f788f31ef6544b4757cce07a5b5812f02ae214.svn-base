package com.hcis.ipr.quartz.mail;

import com.hcis.ipanther.core.utils.AppConfig;
import com.hcis.ipr.intellectual.cost.dao.MailConfigDao;
import com.hcis.ipr.intellectual.cost.dto.PatentCostMonitorDto;
import com.hcis.ipr.intellectual.cost.enumeration.CostType;
import com.hcis.ipr.intellectual.cost.service.MailService;
import com.hcis.ipr.intellectual.cost.service.PatentCostMonitorService;
import freemarker.template.Template;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.ui.freemarker.FreeMarkerTemplateUtils;
import org.springframework.web.servlet.view.freemarker.FreeMarkerConfigurer;

import javax.annotation.Resource;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * @author zhw
 * @date 2019/9/23
 **/
public class MailQuartzJob{

    protected Logger logger= LoggerFactory.getLogger(getClass());

    @Resource
    private MailService mailService;
    @Resource
    private MailConfigDao mailDao;
    @Resource
    private PatentCostMonitorService patentCostMonitorService;

    @Autowired
    private JavaMailSenderImpl mailSender;
    @Resource(name="mailFreeMarkerConfigurer")
    private FreeMarkerConfigurer mailFreeMarkerConfigurer;


    /**
     *邮件提醒（紧急）每天一次
     */
    public void run4Urgent(){
        //年费
        run(31, -30, CostType.ANNUAL_FEE.getType());
        //官费
        run(-1, 31, CostType.GOVERNMENT_FEE.getType());
    }

    /**
     * 年费邮件提醒（滞纳） 七天一次
     */
    public void run4Stagnate(){
        //年费
        run(-29, -180, CostType.ANNUAL_FEE.getType());
        //官费
        run(30, Integer.MAX_VALUE, CostType.GOVERNMENT_FEE.getType());
    }

    public void run(Integer max, Integer min, Integer costType) {
        try {
            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
            List<PatentCostMonitorDto> list = patentCostMonitorService.findEmails(max, min, costType);

            for (PatentCostMonitorDto monitor : list) {

                // 邮箱模板的信息
                String email = monitor.getEmail();
                try {
                    MimeMessage mailMsg = mailSender.createMimeMessage();
                    MimeMessageHelper messageHelper = new MimeMessageHelper(mailMsg, true, "UTF-8");
                    // 接收邮箱
                    messageHelper.setTo(email);
                    // 设置自定义发件人昵称
                    String nickname = javax.mail.internet.MimeUtility
                            .encodeText(AppConfig.getProperty("common", "mail.nickname"));
                    String username = mailSender.getUsername();
                    // 发送邮箱
                    messageHelper.setFrom(new InternetAddress(nickname + " <" + username + ">"));
                    // 邮件标题
                    messageHelper.setSubject("专利费用缴费提醒信息");
                    // 内容模板
                    Template tpl = this.mailFreeMarkerConfigurer.getConfiguration().getTemplate("sendEmailCostWarn.ftl");
                    Map<String, Object> args = new HashMap<String, Object>();
                    Calendar cal = Calendar.getInstance();
                    args.put("patentName", monitor.getPatentName());
                    args.put("appNumber", monitor.getAppNumber());
                    args.put("costName", monitor.getCostName());
                    args.put("standardDate", format.format(monitor.getStandardDate()));
                    String html = FreeMarkerTemplateUtils.processTemplateIntoString(tpl, args);
                    // 邮件内容,true 表示启动HTML格式的邮件
                    messageHelper.setText(html, true);
                    // 发送
                    mailSender.send(mailMsg);
                } catch (Exception e) {
                    logger.error(e.getMessage(), e);
                }
            }

        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

    }

}
