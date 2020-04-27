package com.hcis.ipr.cms.freemarker.directive;

import java.io.IOException;
import java.io.Writer;
import java.util.List;
import java.util.Map;

import com.hcis.ipanther.core.utils.JsonUtil;
import com.hcis.ipr.cms.common.utils.CmsCommonDirective;

import freemarker.core.Environment;
import freemarker.ext.beans.BeansWrapper;
import freemarker.ext.beans.MapModel;
import freemarker.template.SimpleNumber;
import freemarker.template.TemplateDirectiveBody;
import freemarker.template.TemplateDirectiveModel;
import freemarker.template.TemplateException;
import freemarker.template.TemplateModel;

/** 
 * <p>Title: InfoAttchsDirective.java</p>
 * 
 * <p>Description: InfoAttchsDirective</p>
 * 参数 
 * attchStr		附件字符串
 * 
 * 返回值
 * attchUrl 	附件地址
 * attchName	附件名称
 * index        索引
 * 
 * <p>Date: May 22, 2012</p>
 * 
 * <p>Time: 9:47:04 PM</p>
 * 
 * <p>Copyright: 2011</p>
 * 
 * <p>Company: freeteam</p>
 * 
 * @author freeteam
 * @version 1.0
 * 
 * <p>============================================</p>
 * <p>Modification History
 * <p>Mender: </p>
 * <p>Date: </p>
 * <p>Reason: </p>
 * <p>============================================</p>
 */
public class InfoAttchsDirective extends CmsCommonDirective implements TemplateDirectiveModel{

	public void execute(Environment env, Map params, TemplateModel[] loopVars, 
			TemplateDirectiveBody body)throws TemplateException, IOException {
		
		//获取参数
		//附件字符串
		String attchStr=getParam(params, "attchStr");
		
		Writer out =env.getOut();
		if (body!=null) {
			//设置循环变量
			if (loopVars!=null && loopVars.length>0 && attchStr.trim().length()>0) {
				
				List<Map<String,Object>> attchs= JsonUtil.fromJson(attchStr, List.class);
				if (attchs!=null && attchs.size()>0) {
					for (int i = 0; i < attchs.size(); i++) {
						if (attchs.get(i)!=null&&(!attchs.get(i).isEmpty())) {
							loopVars[0]=new MapModel(attchs.get(i),new BeansWrapper());  
							if (loopVars.length>1) {
								loopVars[1]=new MapModel(attchs.get(i),new BeansWrapper());  
							}
							if(loopVars.length>2){
								loopVars[2]=new SimpleNumber(i);
							}
							body.render(env.getOut()); 
						}
					}
				} 
			}
		}
	}
}
