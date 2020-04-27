package com.hcis.ipanther.core.utils;

import java.io.IOException;
import java.io.UnsupportedEncodingException;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.NameValuePair;
import org.apache.commons.httpclient.methods.PostMethod;

/**
 * @author lianghuahuang
 *
 */
public class HttpPostClient {
	
	public static String post(String host,int port,String url,NameValuePair[] newValuePairArray){
			HttpClient client = new HttpClient();  
			client.getHostConfiguration().setHost(host, port, "http");   
		 	PostMethod post = new PostMethod(url);   	  
	        post.setRequestBody(newValuePairArray);   
	        
	        String response=null;
			try {
				client.executeMethod(post);   
				response = new String(post.getResponseBodyAsString());
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}     
	        post.releaseConnection(); 
	        return response;
	}
}
