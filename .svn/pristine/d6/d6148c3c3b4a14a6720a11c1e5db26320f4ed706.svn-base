package com.hcis.ipanther.core.filter;
 
import java.io.IOException;
import java.util.Map;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.hcis.ipanther.core.utils.StringFilterUtils;
/**
 * 非法字符过滤
 * @author http://www.oschina.net/code/snippet_2426852_49890
 * @author wuwentao
 * @date 2015年11月23日
 */
public class IllegalFilter implements Filter {
 
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        // TODO Auto-generated method stub
 
    }
 
    @Override
    public void doFilter(ServletRequest req, ServletResponse res,
            FilterChain chain) throws IOException, ServletException {
         
        HttpServletRequest request = (HttpServletRequest) req;
        HttpServletResponse response = (HttpServletResponse) res;
         
        HTMLCharacterRequest myRequest = new HTMLCharacterRequest(request);
         
        chain.doFilter(myRequest, response);
    }
 
    @Override
    public void destroy() {
        // TODO Auto-generated method stub
 
    }
 
}
 
class HTMLCharacterRequest extends HttpServletRequestWrapper {
	protected Logger logger = LoggerFactory.getLogger(getClass());
	
    HttpServletRequest request;
 
    public HTMLCharacterRequest(HttpServletRequest request) {
        super(request);
        this.request = request;
    }
 
    // 对需要增强方法 进行覆盖
    @Override
    public Map getParameterMap() {
        Map<String, String[]> parameterMap = request.getParameterMap();
        for (String parameterName : parameterMap.keySet()) {
        	String[] values = parameterMap.get(parameterName);
            if (values != null) {
                for (int i = 0; i < values.length; i++) {
                    try {
                        values[i] = StringFilterUtils.filter(values[i]);
                    } catch (Exception e) {
                    	logger.error(e.getMessage(),e);
                    }
                }
            }
        }
        return parameterMap;
    }
 
    /*@Override
    public String getParameter(String name) {
        Map<String, String[]> parameterMap = getParameterMap();
        String[] values = parameterMap.get(name);
        if (values == null) {
            return null;
        }
        // 取回参数的第一个值
        return values[0]; 
    }*/
 
    @Override
    public String[] getParameterValues(String name) {
        Map<String, String[]> parameterMap = getParameterMap();
        String[] values = parameterMap.get(name);
        return values;
    }
 
}