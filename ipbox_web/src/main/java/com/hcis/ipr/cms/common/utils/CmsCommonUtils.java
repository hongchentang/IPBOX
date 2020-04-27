package com.hcis.ipr.cms.common.utils;

import java.io.File;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;

public class CmsCommonUtils {

	public static final String JQUYER_VERSION="jquery-1.11.1.min.js";
	
	
	
	/**
	 * Replace win separator.
	 *
	 * @param path
	 *            the path
	 * @return the string
	 */
	public static String replaceWinSeparator(String path) {
		if (StringUtils.isNotEmpty(path) && StringUtils.contains(path, "\\")) {
			return path = StringUtils.replace(path, "\\", "/");
		}
		return path;
	}

	/**
	 * Replace unix separator.
	 *
	 * @param path
	 *            the path
	 * @return the string
	 */
	public static String replaceUnixSeparator(String path) {
		if (StringUtils.isNotEmpty(path) && StringUtils.contains(path, "//")) {
			return path = StringUtils.replace(path, "//", "/");
		}
		return path;
	}

	/**
	 * 获取目录下所有文件
	 * 
	 * @param folder
	 * @return
	 */
	public static List<File> getFiles(String folder) {
		File file = new File(folder);
		List<File> files = new ArrayList<File>();
		if (file.exists()) {
			File[] sonFiles = file.listFiles();
			if (sonFiles != null && sonFiles.length > 0) {
				for (int i = 0; i < sonFiles.length; i++) {
					if (!sonFiles[i].isDirectory()) {
						files.add(sonFiles[i]);
					}
				}
			}
		}
		return files;
	}

	/**
	 * 获取目录下所有文件夹
	 * 
	 * @param folder
	 * @return
	 */
	public static List<File> getFolders(String folder) {
		File file = new File(folder);
		List<File> files = new ArrayList<File>();
		if (file.exists()) {
			File[] sonFiles = file.listFiles();
			if (sonFiles != null && sonFiles.length > 0) {
				for (int i = 0; i < sonFiles.length; i++) {
					if (sonFiles[i].isDirectory()) {
						files.add(sonFiles[i]);
					}
				}
			}
		}
		return files;
	}

	/**
	 * 判断是否有子目录
	 * 
	 * @param folder
	 * @return
	 */
	public static boolean hasSonFolder(String folder) {
		File file = new File(folder);
		return hasSonFolder(file);
	}

	/**
	 * 判断是否有子目录
	 * 
	 * @param folder
	 * @return
	 */
	public static boolean hasSonFolder(File file) {
		if (file.exists()) {
			File[] sonFiles = file.listFiles();
			if (sonFiles != null && sonFiles.length > 0) {
				for (int i = 0; i < sonFiles.length; i++) {
					if (sonFiles[i].isDirectory()) {
						return true;
					}
				}
			}
		}
		return false;
	}
	

	
	/**
	 * 获取本机IP 机器名
	 * Gets the ip address.
	 *
	 * @return the ip address
	 */
	public static String getIpAddress() {
		InetAddress addr=null;
		try {
			addr = InetAddress.getLocalHost();
		} catch (UnknownHostException e) {
			e.printStackTrace();
		}
		String ip=addr.getHostAddress().toString();//获得本机IP
		String address=addr.getHostName().toString();
		return ip;
	}

	public static List<File> getFiles(String folder, String split) {
		List<File> list= getFiles(folder);
		List<File> returnList=new ArrayList<File>();
		for (File file : list) {
			if(file!=null&&file.getName().contains(split)){
				returnList.add(file);
			}
		}
		return returnList;
	}
	
	/**
	 * 获取目录下所有目录
	 * 
	 * @param folder
	 * @return
	 */
	public static List<File> getDirectory(String folder) {
		File file = new File(folder);
		List<File> files = new ArrayList<File>();
		if (file.exists()) {
			File[] sonFiles = file.listFiles();
			if (sonFiles != null && sonFiles.length > 0) {
				for (int i = 0; i < sonFiles.length; i++) {
					if (sonFiles[i].isDirectory()) {
						files.add(sonFiles[i]);
					}
				}
			}
		}
		return files;
	}

	public static List<File> getDirectory( String folder, String split) {
		File file = new File(folder);
		List<File> files = new ArrayList<File>();
		if (file.exists()) {
			File[] sonFiles = file.listFiles();
			if (sonFiles != null && sonFiles.length > 0) {
				for (int i = 0; i < sonFiles.length; i++) {
					if (sonFiles[i].isDirectory()) {
						files.add(sonFiles[i]);
					}
					if(sonFiles[i].isFile()){
						String fileExt=StringUtils.substringAfterLast(sonFiles[i].getName(), ".");
						if(sonFiles[i]!=null&&StringUtils.contains(split, fileExt)){
							files.add(sonFiles[i]);
						}
					}
				}
			}
		}
		return files;
	}
 
	
	/**
	 * Checks for son folder and filse.
	 *
	 * @param file the file
	 * @param split the split
	 * @return true, if successful
	 */
	public static boolean hasSonFolderAndFilse(File file, String split) {
		if (file.exists()) {
			File[] sonFiles = file.listFiles();
			if (sonFiles != null && sonFiles.length > 0) {
				for (int i = 0; i < sonFiles.length; i++) {
						if (sonFiles[i].isDirectory()) {
							return true;
						}
						if(sonFiles[i].isFile()){
							String fileExt=StringUtils.substringAfterLast(sonFiles[i].getName(), ".");
							if(sonFiles[i]!=null&&StringUtils.contains(split, fileExt)){
								return true;
							}
						}
				}
			}
		}
		return false;
	}
	
	/**
	 * Checks for son folder and filse.
	 *
	 * @param folder the folder
	 * @param split the split
	 * @return true, if successful
	 */
	public static boolean hasSonFolderAndFilse(String folder, String split) {
		File file = new File(folder);
		return hasSonFolderAndFilse(file,split);
	}

	public static List<File> getDirectory(String folder, String[] fileExt) {
		StringBuffer buffer=new StringBuffer();
		for (String split : fileExt) {
			buffer.append(split);
		}
		return getDirectory(folder,buffer.toString());
	}

	public static boolean hasSonFolderAndFilse(File file, String[] fileExt) {
		StringBuffer buffer=new StringBuffer();
		for (String split : fileExt) {
			buffer.append(split);
		}
		return hasSonFolderAndFilse(file,buffer.toString());
	}
}