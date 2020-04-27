/**
 * 
 */
package com.hcis.ipanther.core.utils;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.awt.image.CropImageFilter;
import java.awt.image.FilteredImageSource;
import java.awt.image.ImageFilter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

import javax.imageio.ImageIO;

import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.StringUtils;

import com.alibaba.simpleimage.ImageFormat;
import com.alibaba.simpleimage.ImageRender;
import com.alibaba.simpleimage.SimpleImageException;
import com.alibaba.simpleimage.render.ReadRender;
import com.alibaba.simpleimage.render.ScaleParameter;
import com.alibaba.simpleimage.render.ScaleRender;
import com.alibaba.simpleimage.render.WriteRender;
import com.hcis.ipanther.core.entity.CompressedImageSize;

/**
 * @author Administrator
 *
 */
public class ImageProcessor {
	public static boolean compressImage(String srcImgPath,String destImgPath,CompressedImageSize cis){
		  File in = new File(srcImgPath);      //原图片
		  File out = new File(destImgPath);       //目的图片
		  if(!out.exists()){
			  try {
				out.createNewFile();
			} catch (IOException e) {
				e.printStackTrace();
				return false;
			}
		  }
		  ScaleParameter scaleParam = new ScaleParameter(cis.getWidth(), cis.getHeight());  //将图像缩略到1024x1024以内，不足1024x1024则不做任何处理

		  FileInputStream inStream = null;
		  FileOutputStream outStream = null;
		  ImageRender wr = null;
		  try {
		      inStream = new FileInputStream(in);
		      outStream = new FileOutputStream(out);
		      ImageRender rr = new ReadRender(inStream);
		      ImageRender sr = new ScaleRender(rr, scaleParam);
		      wr = new WriteRender(sr, outStream, ImageFormat.getImageFormat(StringUtils.substringAfterLast(srcImgPath, ".")));
		      wr.render();                            //触发图像处理
		      return true;
		  } catch(Exception e) {
		      e.printStackTrace();
		      return false;
		  } finally {
		      IOUtils.closeQuietly(inStream);         //图片文件输入输出流必须记得关闭
		      IOUtils.closeQuietly(outStream);
		      if (wr != null) {
		          try {
		              wr.dispose();                   //释放simpleImage的内部资源
		          } catch (SimpleImageException ignore) {
		              // skip ... 
		          }
		      }
		  }
	}
	
	/** 
	    *  缩放后裁剪图片方法 
	    * @param srcImageFile 源文件 
	    * @param x  x坐标 
	    * @param y  y坐标 
	    * @param destWidth 最终生成的图片宽 
	    * @param destHeight 最终生成的图片高 
	    * @param finalWidth  缩放宽度 
	    * @param finalHeight  缩放高度 
	    */  
	   public static void abscut(String srcImageFile, int x, int y, int destWidth,  
	                             int destHeight,int finalWidth,int finalHeight) {  
	       try {  
	           Image img;  
	           ImageFilter cropFilter;  
	           // 读取源图像  
	           BufferedImage bi = ImageIO.read(new File(srcImageFile));  
	           int srcWidth = bi.getWidth(); // 源图宽度  
	           int srcHeight = bi.getHeight(); // 源图高度  
	  
	           if (srcWidth >= destWidth && srcHeight >= destHeight) {  
	               Image image = bi.getScaledInstance(finalWidth, finalHeight,Image.SCALE_DEFAULT);//获取缩放后的图片大小  
	               cropFilter = new CropImageFilter(x, y, destWidth, destHeight);  
	               img = Toolkit.getDefaultToolkit().createImage(  
	                       new FilteredImageSource(image.getSource(), cropFilter));  
	               BufferedImage tag = new BufferedImage(destWidth, destHeight,  
	                       BufferedImage.TYPE_INT_RGB);  
	               Graphics g = tag.getGraphics();  
	               g.drawImage(img, 0, 0, null); // 绘制截取后的图  
	               g.dispose();  
	               // 输出为文件  
	               ImageIO.write(tag, "JPEG", new File(srcImageFile));  
	           }  
	       } catch (Exception e) {  
	           e.printStackTrace();  
	       }  
	   } 
	   

	   
	   public static void abscut(String srcImageFile,String destImageFile, int x, int y, int destWidth,
				int destHeight,int finalWidth,int finalHeight) {
			try {
				Image img;
				ImageFilter cropFilter;
				// 读取源图像
				BufferedImage bi = ImageIO.read(new File(srcImageFile));
				int srcWidth = bi.getWidth(); // 源图宽度
				int srcHeight = bi.getHeight(); // 源图高度

				if (srcWidth >= destWidth && srcHeight >= destHeight) {
					 int x1 = x*srcWidth/finalWidth;
					                  int y1 = y*srcHeight/finalHeight;
					                 int w1 = destWidth*srcWidth/finalWidth;
					                int h1 = destHeight*srcHeight/finalHeight;
					Image image = bi.getScaledInstance(srcWidth, srcHeight,
							Image.SCALE_DEFAULT);// 获取缩放后的图片大小
					cropFilter = new CropImageFilter(x1, y1, w1, h1);
					img = Toolkit.getDefaultToolkit().createImage(
							new FilteredImageSource(image.getSource(), cropFilter));
					BufferedImage tag = new BufferedImage(w1, h1,
							BufferedImage.TYPE_INT_RGB);
					Graphics g = tag.getGraphics();
					g.drawImage(img, 0, 0, null); // 绘制截取后的图
					g.dispose();
					File file = new File(destImageFile);
					if(!file.exists()){
						file.createNewFile();
					}
					// 输出为文件
					ImageIO.write(tag, "JPEG", new File(destImageFile));
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	   
	   
}
