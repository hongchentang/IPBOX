package com.hcis.ipr.wechat.menu.web;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.net.URLConnection;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.servlet.http.HttpServletResponse;
import javax.swing.text.html.HTMLDocument.Iterator;

import org.apache.commons.httpclient.HttpException;
import org.apache.commons.lang3.StringUtils;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.hcis.ipanther.core.spring.mvc.bind.annotation.FormModel;
import com.hcis.ipanther.core.utils.JSONUtils;
import com.hcis.ipanther.core.web.vo.SearchParam;
import com.hcis.ipr.core.entity.Response;
import com.hcis.ipr.core.web.controller.BaseController;
import com.hcis.ipr.wechat.menu.entity.Menu;
import com.hcis.ipr.wechat.menu.service.IMenuService;
/**
 * 微信后台菜单管理相关 
 * @date 2016年11月1日
 */
@Controller
@RequestMapping("/manage/wechat/menu/")
public class MenuController extends BaseController{
	@Autowired
	private IMenuService menuService;
	
	/**
	 * 微信菜单首页
	 * @param searchParam
	 * @return
	 */
   @RequestMapping("list")
   public ModelAndView list(SearchParam searchParam){
	   ModelAndView mv =new ModelAndView();
	   return mv;
   }
   
   
   /**
    * 微信菜单树列表
    * @param response
    * @return
    */
 	@RequestMapping("/menuList")
 	@ResponseBody
 	public String menuList(HttpServletResponse response){
 		String treeView=null;
 		List<Map<String, Object>>list =menuService.treeView(new SearchParam(false));
 		treeView=JSONUtils.getJSONString(list);
 		return this.renderText(treeView,response);
 	}
 	
 	/**
 	 * 一级菜单列表
 	 * @param response
 	 * @return
 	 */
 	@RequestMapping("/menuParentList")
 	@ResponseBody
 	public String menuParentList(HttpServletResponse response){
 		String treeView=null;
 		SearchParam searchParam=new SearchParam(false);
		searchParam.getParamMap().put("isParent",true);
 		List<Map<String, Object>>list =menuService.treeView(searchParam);
 		treeView=JSONUtils.getJSONString(list);
 		return this.renderText(treeView,response);
 	}
 	
 	    
 	/**
 	 * 修改菜单
 	 * @param menu
 	 * @return
 	 */
	@RequestMapping("/edit")
	public ModelAndView edit(@FormModel("menu")Menu menu) {
		ModelAndView mv = this.newModelAndView();			
		String id = menu.getId();
		if(StringUtils.isNotEmpty(id)) {
			mv.addObject("menu", menuService.read(id));			
		}
		SearchParam searchParam=new SearchParam();
		searchParam.getParamMap().put("parent",true);	
		mv.addObject("parentNodes",menuService.countByMap(searchParam.getParamMap()));
		return mv;
	}
	
	/**
	 * 保存
	 * @param menu
	 * @return
	 */
	@RequestMapping("/save")
	public @ResponseBody Response save(@FormModel("menu")Menu menu) {
		//无父节点的，设置为0
		if(StringUtils.isBlank(menu.getParentId())){
			menu.setParentId("0");
		}
		String errorMsg =menuService.save(menu,this.getLoginUser().getId());
		return Response.newResponse(errorMsg);
	}
	
	/**
	 * 删除
	 * @param menu
	 * @return
	 */
	@RequestMapping("/delete")
	public @ResponseBody Response delete(@FormModel("menu")Menu menu) {
		int count=menuService.delete(menu,this.getLoginUser().getId());
		return Response.newResponse(count);
	}
	
   /**
	 * 生成菜单
	 * @param response
	 */
	@RequestMapping("/generate")
	@ResponseBody
	public Response generate(HttpServletResponse response) {
		String errorMsg = menuService.generate();
		return Response.newResponse(errorMsg);
	}
	
	
	
	/**
	 * 测试获取专利数据，从出版社那边获取数据,进入页面进行授权
	 *@author Administrator 
	 *@param response
	 */
	@RequestMapping("/getPatent")
	
	public  ModelAndView getPatent(SearchParam searchParam){
		ModelAndView mv =new ModelAndView();
		return mv;
   }
	
	/**
	 * 
	 */
	
	   
	@RequestMapping("/authoring") 
	@ResponseBody
	public Response authoringUse(HttpServletResponse response)
	{
		 
		String param_client_id="3FB9DC1CD0FFABA133F404DA9A965CDC";
		String param_redirect_uri="http://hufxgu.natappfree.cc/site/index.do";	   
	  	String url="https://open.cnipr.com/gdip/authorize?client_id="+param_client_id+"&redirect_uri="+param_redirect_uri+"&response_type="+"code"+"&stats=1";
	  		  	
	  	String charset="UTF-8";
	  	String result = "";
		BufferedReader in = null;
		try {
			URL realUrl = new URL(url);
			// 打开和URL之间的连接
			URLConnection connection = realUrl.openConnection();
			
            // 设置通用的请求属性
			connection.setRequestProperty("accept", "*/*");
			connection.setRequestProperty("connection", "Keep-Alive");
			connection.setRequestProperty("user-agent","Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1;SV1)");
			// 建立实际的连接
			connection.connect();
			// 定义 BufferedReader输入流来读取URL的响应
			in = new BufferedReader(new InputStreamReader(connection.getInputStream(), charset));
			String line;
			while ((line = in.readLine()) != null) {
				result += line;
			}
		} catch (Exception e) {
			System.out.println("发送GET请求出现异常！" + e);
			e.printStackTrace();
		}
		// 使用finally块来关闭输入流
		finally {
			try {
				if (in != null) {
					in.close();
				}
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
		return Response.newResponse(result);
	}
	
}