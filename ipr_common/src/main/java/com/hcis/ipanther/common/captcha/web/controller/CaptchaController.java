package com.hcis.ipanther.common.captcha.web.controller;

import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;

import javax.annotation.Resource;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.hcis.ipr.core.web.controller.BaseController;
import com.octo.captcha.service.image.ImageCaptchaService;
import com.sun.image.codec.jpeg.JPEGCodec;
import com.sun.image.codec.jpeg.JPEGImageEncoder;

/**
 * 验证码
 * @author wuwentao
 * @date 2015年3月24日
 */
@SuppressWarnings("restriction")
@Controller
@RequestMapping("/common/captcha")
public class CaptchaController extends BaseController{
	
	private final static Log log = LogFactory.getLog(CaptchaController.class);
	
	@Resource(name = "imageCaptchaService")  
    private ImageCaptchaService imageCaptchaService;

	@RequestMapping(value = "/generateImage")
    public void ImageCaptcha(HttpServletRequest request , HttpServletResponse response , Model model)   
     throws ServletException, IOException{ 
        byte[] captchaChallengeAsJpeg = null;  
        ByteArrayOutputStream jpegOutputStream = new ByteArrayOutputStream();  
        try {
            String captchaId = request.getSession().getId();
            BufferedImage challenge = imageCaptchaService.getImageChallengeForID(captchaId, request .getLocale());  
            JPEGImageEncoder jpegEncoder = JPEGCodec.createJPEGEncoder(jpegOutputStream);  
            jpegEncoder.encode(challenge);
        } catch (IllegalArgumentException e) {
        	log.error("获取验证码出错", e);
        }  
          
        captchaChallengeAsJpeg = jpegOutputStream.toByteArray();    
        response.setHeader("Cache-Control", "no-store");    
        response.setHeader("Pragma", "no-cache");    
        response.setDateHeader("Expires", 0);    
        response.setContentType("image/jpeg");    
        ServletOutputStream responseOutputStream = response.getOutputStream();    
        responseOutputStream.write(captchaChallengeAsJpeg);    
        responseOutputStream.flush();    
        responseOutputStream.close();  
        jpegOutputStream.close();
    }  
}
