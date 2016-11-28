/**
 * File：LetusResult.java
 * Package：com.letus.common.pojo
 * Author：xfyin
 * Date：2016-11-27 下午7:36:07
 * Copyright (C) 2016-2016 letus179-Copyright
 */
package com.letus.common.pojo;

import java.util.List;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * 自定义响应数据格式
 * 
 * @author xfyin
 * 
 */
public class LetusResult {
  
  /**
   * 定义json对象
   */
  private static final ObjectMapper MAPPER = new ObjectMapper();
  
  /**
   * 响应业务状态
   */
  private int status;
  
  /**
   * 响应消息
   */
  private String msg;
  
  /**
   * 响应数据
   */
  private Object data;
  
  /**
   * 默认
   */
  public LetusResult() {
  }
  
  /**
   * 构造方法
   * 
   * @param data
   *        数据
   */
  public LetusResult(Object data) {
    super();
    this.data = data;
    this.status = 200;
    this.msg = "OK";
  }
  
  /**
   * 构造方法
   * 
   * @param status
   *        状态
   * @param msg
   *        信息
   * @param data
   *        数据
   */
  public LetusResult(int status, String msg, Object data) {
    super();
    this.status = status;
    this.msg = msg;
    this.data = data;
  }
  
  /**
   * 成功
   * 
   * @return 对象
   */
  public static LetusResult ok() {
    return new LetusResult(null);
  }
  
  /**
   * 成功，并返回带有数据的对象
   * 
   * @param data
   *        数据
   * @return 对象
   */
  public static LetusResult ok(Object data) {
    return new LetusResult(data);
  }
  
  /**
   * 创建一个不带数据的对象
   * 
   * @param status
   *        状态
   * @param msg
   *        信息
   * @return 对象
   */
  public static LetusResult build(int status, String msg) {
    return new LetusResult(status, msg, null);
  }
  
  /**
   * 创建一个带有完整数据的对象
   * 
   * @param status
   *        状态
   * @param msg
   *        信息
   * @param data
   *        数据
   * @return 对象
   */
  public static LetusResult build(int status, String msg, Object data) {
    return new LetusResult(status, msg, data);
  }
  
  /**
   * json字符串转对象
   * 
   * @param jsonData
   *        json字符串
   * @return 对象
   */
  public static LetusResult format(String jsonData) {
    try {
      return MAPPER.readValue(jsonData, LetusResult.class);
    }
    catch (Exception e) {
      e.printStackTrace();
      return null;
    }
  }
  
  /**
   * 将json结果集转换为LetusResult对象
   * 
   * @param jsonData
   *        json字符串
   * @param clazz
   *        类型
   * @return LetusResult对象
   */
  public static LetusResult formatToPojo(String jsonData, Class<?> clazz) {
    try {
      if (clazz == null) {
        return format(jsonData);
      }
      JsonNode node = MAPPER.readTree(jsonData);
      JsonNode data = node.get("data");
      Object obj = null;
      if (data.isObject()) {
        obj = MAPPER.readValue(data.traverse(), clazz);
      }
      else if (data.isTextual()) {
        obj = MAPPER.readValue(data.asText(), clazz);
      }
      return build(node.get("status").intValue(), node.get("msg").asText(), obj);
    }
    catch (Exception e) {
      e.printStackTrace();
      return null;
    }
  }
  
  /**
   * Object 是集合转化
   * 
   * @param jsonData
   *        json字符串
   * @param clazz
   *        类型
   * @return LetusResult对象
   */
  public static LetusResult formatToList(String jsonData, Class<?> clazz) {
    try {
      JsonNode node = MAPPER.readTree(jsonData);
      JsonNode data = node.get("data");
      Object obj = null;
      if (data != null && data.isArray() && data.size() > 0) {
        obj = MAPPER.readValue(data.traverse(), MAPPER.getTypeFactory().constructCollectionType(List.class, clazz));
      }
      return build(node.get("status").intValue(), node.get("msg").asText(), obj);
    }
    catch (Exception e) {
      e.printStackTrace();
      return null;
    }
  }
  
  /**
   * @return status
   */
  public int getStatus() {
    return status;
  }
  
  /**
   * @param status
   *        set status
   */
  public void setStatus(int status) {
    this.status = status;
  }
  
  /**
   * @return msg
   */
  public String getMsg() {
    return msg;
  }
  
  /**
   * @param msg
   *        set msg
   */
  public void setMsg(String msg) {
    this.msg = msg;
  }
  
  /**
   * @return data
   */
  public Object getData() {
    return data;
  }
  
  /**
   * @param data
   *        set data
   */
  public void setData(Object data) {
    this.data = data;
  }
  
}
