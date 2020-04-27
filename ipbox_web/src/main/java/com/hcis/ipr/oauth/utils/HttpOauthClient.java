package com.hcis.ipr.oauth.utils;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.util.List;

/**
 * @author zhw
 * @date 2019/11/20
 **/
public class HttpOauthClient {

    public String get(String url){
        CloseableHttpClient httpClient = null;
        CloseableHttpResponse httpResponse = null;
        String result = "";
        try {
            httpClient = HttpClients.createDefault();
            HttpGet httpGet = new HttpGet(url);
            httpGet.setHeader("Accept", "application/json");
            RequestConfig requestConfig = RequestConfig.custom()
                    .setConnectionRequestTimeout(35000)
                    .setConnectTimeout(35000)
                    .setSocketTimeout(60000)
                    .build();
            httpGet.setConfig(requestConfig);

            httpResponse = httpClient.execute(httpGet);

            HttpEntity entity = httpResponse.getEntity();
            result = EntityUtils.toString(entity);

            System.out.println("--------> " + result);
            return result;
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            if(httpResponse != null){
                try {
                    httpResponse.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            if(httpClient != null){
                try {
                    httpClient.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

        return null;
    }

    public String post(String url, String requestParamJson){

        CloseableHttpClient client = HttpClients.createDefault();
        CloseableHttpResponse response = null;
        String result = "";
        try {
            HttpPost post = new HttpPost(url);
            post.addHeader("Content-type", "application/x-www-form-urlencoded;");
            //post.addHeader("Content-type", "application/json;");
            StringEntity entity = new StringEntity(requestParamJson);
            post.setEntity(entity);

            response = client.execute(post);
            result = EntityUtils.toString(response.getEntity(), "utf-8");
            System.out.println("---------> " + result);

            return result;
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            if(response != null){
                try {
                    response.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if(client != null){
                try {
                    client.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

        }

        return null;
    }

    public String postFormUrlencoded(String url, List<NameValuePair> params){

        CloseableHttpClient client = HttpClients.createDefault();
        CloseableHttpResponse response = null;
        String result = "";
        try {

            HttpPost post = new HttpPost(url);
            post.addHeader("Content-type", "application/x-www-form-urlencoded;");

            UrlEncodedFormEntity encodedFormEntity = new UrlEncodedFormEntity(params, "UTF-8");
            encodedFormEntity.setContentType("application/x-www-form-urlencoded; charset=UTF-8");
            post.setEntity(encodedFormEntity);

            response = client.execute(post);
            result = EntityUtils.toString(response.getEntity(), "UTF-8");
            System.out.println("----------> " + result);

            return result;
        }catch (Exception e){
            if(response != null){
                try {
                    response.close();
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            }

            if(client != null){
                try {
                    client.close();
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            }
        }

        return null;
    }
}
