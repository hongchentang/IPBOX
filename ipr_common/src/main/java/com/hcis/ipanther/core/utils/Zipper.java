package com.hcis.ipanther.core.utils;


import java.io.File;

import org.apache.tools.ant.BuildException;
import org.apache.tools.ant.Project;
import org.apache.tools.ant.taskdefs.Expand;
import org.apache.tools.ant.taskdefs.Zip;
import org.apache.tools.ant.types.FileSet;

public class Zipper {
	public final static String encoding = "GBK";
	
	/**
	 * 压缩文件，默认编码格式为GBK
	 * @param srcPathname
	 * @param zipFilepath
	 * @throws BuildException
	 * @throws RuntimeException
	 */
	public static void zip(String srcPathname, String zipFilepath)
			throws BuildException{
		zip(srcPathname,zipFilepath,encoding);
	}
	/**
	 * 压缩文件
	 * @param srcPathname 压缩文件夹目录
	 * @param zipFilepath 压缩文件路径
	 * @param encoding 编码格式
	 * @throws BuildException
	 * @throws RuntimeException
	 */
	public static void zip(String srcPathname, String zipFilepath,String encoding)
			throws BuildException{
		File file = new File(srcPathname);
		if (!file.exists())
			throw new RuntimeException("source file or directory "
					+ srcPathname + " does not exist.");

		Project proj = new Project();
		FileSet fileSet = new FileSet();
		fileSet.setProject(proj);
		// 判断是目录还是文件
		if (file.isDirectory()) {
			fileSet.setDir(file);
			// ant中include/exclude规则在此都可以使用
			// 比如:
			// fileSet.setExcludes("***.xls");
		} else {
			fileSet.setFile(file);
		}

		Zip zip = new Zip();
		zip.setProject(proj);
		zip.setDestFile(new File(zipFilepath));
		zip.addFileset(fileSet);
		zip.setEncoding(encoding);
		zip.execute();
	}
	/**
	 * 解压缩文件 默认编码格式为GBK
	 * @param zipFilepath
	 * @param destDir
	 * @throws BuildException
	 * @throws RuntimeException
	 */
	public static void unzip(String zipFilepath, String destDir)
			throws BuildException{
		unzip(zipFilepath,destDir,encoding);
	}
	/**
	 * 解压缩文件
	 * @param zipFilepath 压缩文件路径
	 * @param destDir 解压缩文件目录
	 * @param 编码格式
	 * @throws BuildException
	 * @throws RuntimeException
	 */
	public static void unzip(String zipFilepath, String destDir,String encoding)
			throws BuildException{
		if (!new File(zipFilepath).exists())
			throw new RuntimeException("zip file " + zipFilepath
					+ " does not exist.");

		Project proj = new Project();
		Expand expand = new Expand();
		expand.setProject(proj);
		expand.setTaskType("unzip");
		expand.setTaskName("unzip");
		expand.setEncoding(encoding);

		expand.setSrc(new File(zipFilepath));
		expand.setDest(new File(destDir));
		expand.execute();
	}
	
}