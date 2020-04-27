package com.hcis.ipanther.core.exception;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;

import com.hcis.ipanther.core.utils.StringPrintWriter;

/**
 * spring mvc 框架下 未捕获异常的处理
 */
public class IPantherHandlerExceptionResolver implements HandlerExceptionResolver{
    private static Logger logger = Logger.getLogger(IPantherHandlerExceptionResolver.class);
    @Override
    public ModelAndView resolveException(HttpServletRequest request,
            HttpServletResponse response, Object handler, Exception ex) {
        logger.error("Catch Exception: ",ex);//把漏网的异常信息记入日志
        Map<String,Object> map = new HashMap<String,Object>();
        StringPrintWriter strintPrintWriter = new StringPrintWriter();
        ex.printStackTrace(strintPrintWriter);
        map.put("errorMsg", strintPrintWriter.getString());//将错误信息传递给view
        return new ModelAndView("error",map);
    }

}
