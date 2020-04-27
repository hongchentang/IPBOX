package com.hcis.ipr.cms.templet.utils;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;

import com.hcis.ipanther.common.dict.utils.DictionaryUtils;
import com.hcis.ipr.cms.common.utils.CmsCommonUtils;

public class TempletUtils {
    
	private static String ROOT_PATH=DictionaryUtils.getEntryValue("CMS_CONFING","TEMPLET_PATH");
	
	public final static String ROOT_SITE_NAME="cms";
	
	public static List<Map<String,Object>> getListTempletFile(String realPath){
//		String path= realPath+ROOT_PATH+"/"+siteName;
//		List<Map<String, Object>> list=getListFiles(path);
//		  List<Map<String, Object>> root=new ArrayList<Map<String,Object>>();
//		  Map<String, Object> map =new HashMap<String, Object>();
//		  map.put("text", "根目录");
//		  map.put("id",   siteName);
//		  if (list!=null&&!list.isEmpty()) {
//			  map.put("children",   list);
//		  }
//		  Map<String, Object> tempMap =new HashMap<String, Object>();
//		  tempMap.put("isDirectory", "true");
//		  map.put("attributes", tempMap);
//		  map.put("state", "closed");
//		  root.add(map);
//		return root;
		return null;
	}
	
	
   public static List<Map<String,Object>> getListFiles(String path){
	   List<Map<String, Object>> list=new ArrayList<Map<String,Object>>();
	   	File file=new File(path);
	   	if(file.exists()){
	   		File[] tempList = file.listFiles();
		   	for (File temp : tempList) {
		   		Map<String, Object> map =new HashMap<String, Object>();
		   		Map<String, Object> tempMap =new HashMap<String, Object>();
			   	  String tempPath=temp.getPath();
				  if(StringUtils.contains(tempPath, "\\")){
					   tempPath= StringUtils.replace(tempPath, "\\", "/");
				  }
				  map.put("text", temp.getName());
				  map.put("id",   tempPath.replace(path, "").trim());
				  if (temp.isDirectory()) {
					  map.put("state", "closed");
					  List<Map<String, Object>> listTemp=getListFiles(tempPath); 
					  map.put("children",   listTemp);
					  tempMap.put("isDirectory", "true");
					  
				  }
				  map.put("attributes", tempMap);
				  list.add(map);
			}
	   	}
	   return list;
   }


	public static List<Map<String, Object>> getListTempletDirectory(String realPath) {
		//String path= realPath+ROOT_PATH+"/"+siteName;
		List<Map<String, Object>> list=getListDirectory(realPath);
		  List<Map<String, Object>> root=new ArrayList<Map<String,Object>>();
		  Map<String, Object> map =new HashMap<String, Object>();
		  map.put("text", "根目录");
		  map.put("id",   realPath);
		  if (list!=null&&!list.isEmpty()) {
			  map.put("children",   list);
		  }
		  Map<String, Object> tempMap =new HashMap<String, Object>();
		  tempMap.put("isDirectory", "true");
		  map.put("attributes", tempMap);
		  map.put("state", "closed");
		  root.add(map);
		return root;
	}
	
	public static List<Map<String, Object>> getListTempletDirectory(String realPath,String replacePath) {
		//String path= realPath+ROOT_PATH+"/"+siteName;
		List<Map<String, Object>> list=getListDirectory(realPath,replacePath);
		  List<Map<String, Object>> root=new ArrayList<Map<String,Object>>();
		  Map<String, Object> map =new HashMap<String, Object>();
		  map.put("text", "根目录");
		  map.put("id",   realPath.replace(replacePath,"/"));
		  if (list!=null&&!list.isEmpty()) {
			  map.put("children",   list);
		  }
		  Map<String, Object> tempMap =new HashMap<String, Object>();
		  tempMap.put("isDirectory", "true");
		  map.put("attributes", tempMap);
		  map.put("state", "closed");
		  root.add(map);
		return root;
	}

	   public static List<Map<String,Object>> getListDirectory(String path,String replacePath){
		   List<Map<String, Object>> list=new ArrayList<Map<String,Object>>();
		   	File file=new File(path);
		   	if(file.exists()){
		   		File[] tempList = file.listFiles();
			   	for (File temp : tempList) {
			   		if (temp.isDirectory()) {
			   			Map<String, Object> map =new HashMap<String, Object>();
			   			Map<String, Object> tempMap =new HashMap<String, Object>();
				   	    String tempPath=temp.getPath();
					    if(StringUtils.contains(tempPath, "\\")){
					    	tempPath= StringUtils.replace(tempPath, "\\", "/");
						}
					    map.put("text", temp.getName());
						map.put("id",  CmsCommonUtils.replaceUnixSeparator(tempPath.replace(replacePath, "/")));
						if (CmsCommonUtils.hasSonFolder(temp)) {
							tempMap.put("isDirectory", "true");
							List<Map<String, Object>> listTemp=getListDirectory(tempPath,replacePath); 
							map.put("children",   listTemp);
							map.put("state", "closed");
						}else {
							tempMap.put("isDirectory", "false");
							map.put("state", "open");
						}
						map.put("attributes", tempMap);
						list.add(map);
					 }
				}
		   	}
		   return list;
	   }
	
	
	
	   public static List<Map<String,Object>> getListDirectory(String path){
		   List<Map<String, Object>> list=new ArrayList<Map<String,Object>>();
		   	File file=new File(path);
		   	if(file.exists()){
		   		File[] tempList = file.listFiles();
			   	for (File temp : tempList) {
			   		if (temp.isDirectory()) {
			   			Map<String, Object> map =new HashMap<String, Object>();
			   			Map<String, Object> tempMap =new HashMap<String, Object>();
				   	    String tempPath=temp.getPath();
					    if(StringUtils.contains(tempPath, "\\")){
					    	tempPath= StringUtils.replace(tempPath, "\\", "/");
						}
					    map.put("text", temp.getName());
						map.put("id",   tempPath);
						if (CmsCommonUtils.hasSonFolder(temp)) {
							tempMap.put("isDirectory", "true");
							List<Map<String, Object>> listTemp=getListDirectory(tempPath); 
							map.put("children",   listTemp);
							map.put("state", "closed");
						}else {
							tempMap.put("isDirectory", "false");
							map.put("state", "open");
						}
						map.put("attributes", tempMap);
						list.add(map);
					 }
				}
		   	}
		   return list;
	   }
	
		
//   public static void listFiles(String path){
//		File file=new File(path);
//	   	File[] tempList = file.listFiles();
//	   System.out.println("该目录下对象个数："+tempList.length);
//		  for (int i = 0; i < tempList.length; i++) {
//		   if (tempList[i].isFile()) {
//		    System.out.println("文     件："+tempList[i]);
//		    System.out.println("文     件--------getName："+tempList[i].getName());
//		    System.out.println("文     件--------getParent："+tempList[i].getParent());
//		   }
//		   if (tempList[i].isDirectory()) {
//			   System.out.println("文件夹："+tempList[i]+"---------"+tempList[i].getPath());
//			   String tempPath=tempList[i].getPath();
//			   if(StringUtils.contains(tempPath, "\\")){
//				   tempPath= StringUtils.replace(tempPath, "\\", "/");
//			   }
//			    System.out.println("文件夹--------getName："+tempList[i].getName());
//			    System.out.println("文件夹--------getParent："+tempList[i].getParent());
//			   listFiles(tempPath);
//		   }
//		  }
//   }
//   
//   
//   public static void main(String[] orgs){
//		listFiles("D:/temp");
//   }
	
}
