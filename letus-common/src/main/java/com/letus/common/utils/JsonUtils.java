/**
 * File：JsonUtils.java
 * Package：com.letus.common.utils
 * Author：xfyin
 * Date：2016-11-27 上午1:10:15
 * Copyright (C) 2016-2016 letus179-Copyright
 */
package com.letus.common.utils;

import java.util.List;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * json相关操作
 * 
 * @author xfyin
 * 
 */
public class JsonUtils {
  
  /**
   * 定义jackson对象
   */
  private static final ObjectMapper MAPPER = new ObjectMapper();
  
  /**
   * 对象转json字符串
   * 
   * @param data
   *        对象
   * @return json字符串
   */
  public static String objectToJson(Object data) {
    try {
      return MAPPER.writeValueAsString(data);
    }
    catch (JsonProcessingException e) {
      e.printStackTrace();
      return null;
    }
  }
  
  /**
   * 将json结果级转换为对象
   * 
   * @param jsonData
   *        json字符串
   * @param beanType
   *        对象中的object类型
   * @return 对象
   */
  public static <T> T jsonToPojo(String jsonData, Class<T> beanType) {
    try {
      return MAPPER.readValue(jsonData, beanType);
    }
    catch (Exception e) {
      e.printStackTrace();
      return null;
    }
  }
  
  /**
   * 将json结果集转换为pojo对象的list
   * 
   * @param jsonData
   *        json字符串
   * @param beanType
   *        pojo对象类型
   * @return list
   */
  public static <T> List<T> jsonToList(String jsonData, Class<T> beanType) {
    JavaType javaType = MAPPER.getTypeFactory().constructParametricType(List.class, beanType);
    try {
      return MAPPER.readValue(jsonData, javaType);
    }
    catch (Exception e) {
      e.printStackTrace();
      return null;
    }
    
  }
  
}
