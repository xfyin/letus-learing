/**
 * File：HttpClientUtil.java
 * Package：com.letus.common.utils
 * Author：xfyin
 * Date：2016-12-4 下午10:31:58
 * Copyright (C) 2016-2016 letus179-Copyright
 */
package com.letus.common.utils;

import java.io.IOException;
import java.net.URI;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

/**
 * httpclient工具类 get post ，带参数不带参数 发送请求
 * 
 * @author xfyin
 * 
 */
public class HttpClientUtil {
  
  /**
   * 发送带参数的get请求
   * 
   * @param url
   *        请求地址
   * @param param
   *        参数
   * @return 响应实体信息
   */
  public static String doGet(String url, Map<String, String> param) {
    
    // 创建Httpclient对象
    CloseableHttpClient httpclient = HttpClients.createDefault();
    
    String resultString = "";
    CloseableHttpResponse response = null;
    try {
      // 创建uri
      URIBuilder builder = new URIBuilder(url);
      if (param != null) {
        for (Map.Entry<String, String> entry : param.entrySet()) {
          builder.addParameter(entry.getKey(), entry.getValue());
        }
      }
      URI uri = builder.build();
      
      // 创建http GET请求
      HttpGet httpGet = new HttpGet(uri);
      
      // 执行请求
      response = httpclient.execute(httpGet);
      // 判断返回状态是否为200
      if (response.getStatusLine().getStatusCode() == 200) {
        resultString = EntityUtils.toString(response.getEntity(), "UTF-8");
      }
    }
    catch (Exception e) {
      e.printStackTrace();
    }
    finally {
      try {
        if (response != null) {
          response.close();
        }
        httpclient.close();
      }
      catch (IOException e) {
        e.printStackTrace();
      }
    }
    return resultString;
  }
  
  /**
   * 发送不带参数的get请求
   * 
   * @param url
   *        请求地址
   * @return 响应实体信息
   */
  public static String doGet(String url) {
    return doGet(url, null);
  }
  
  /**
   * 发送带参数的post请求
   * 
   * @param url
   *        请求地址
   * @param param
   *        请求参数
   * @return 响应的实体信息
   */
  public static String doPost(String url, Map<String, String> param) {
    // 创建Httpclient对象
    CloseableHttpClient httpClient = HttpClients.createDefault();
    CloseableHttpResponse response = null;
    String resultString = "";
    try {
      // 创建Http Post请求
      HttpPost httpPost = new HttpPost(url);
      // 创建参数列表
      if (param != null) {
        List<NameValuePair> paramList = new ArrayList<>();
        for (Map.Entry<String, String> entry : param.entrySet()) {
          paramList.add(new BasicNameValuePair(entry.getKey(), entry.getValue()));
        }
        // 模拟表单
        UrlEncodedFormEntity entity = new UrlEncodedFormEntity(paramList);
        httpPost.setEntity(entity);
      }
      // 执行http请求
      response = httpClient.execute(httpPost);
      resultString = EntityUtils.toString(response.getEntity(), "utf-8");
    }
    catch (Exception e) {
      e.printStackTrace();
    }
    finally {
      try {
        response.close();
      }
      catch (IOException e) {
        // TODO Auto-generated catch block
        e.printStackTrace();
      }
    }
    
    return resultString;
  }
  
  /**
   * 发送不带参数的post请求
   * 
   * @param url
   *        请求地址
   * @return 响应的实体信息
   */
  public static String doPost(String url) {
    return doPost(url, null);
  }
  
  /**
   * 发送参数为json格式的post请求
   * 
   * @param url
   *        请求地址
   * @param json
   *        json格式的参数
   * @return 响应的实体信息
   */
  public static String doPostJson(String url, String json) {
    // 创建Httpclient对象
    CloseableHttpClient httpClient = HttpClients.createDefault();
    CloseableHttpResponse response = null;
    String resultString = "";
    try {
      // 创建Http Post请求
      HttpPost httpPost = new HttpPost(url);
      // 创建请求内容
      StringEntity entity = new StringEntity(json, ContentType.APPLICATION_JSON);
      httpPost.setEntity(entity);
      // 执行http请求
      response = httpClient.execute(httpPost);
      resultString = EntityUtils.toString(response.getEntity(), "utf-8");
    }
    catch (Exception e) {
      e.printStackTrace();
    }
    finally {
      try {
        response.close();
      }
      catch (IOException e) {
        e.printStackTrace();
      }
    }
    
    return resultString;
  }
}
