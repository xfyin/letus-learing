/**
 * File：IDUtil.java
 * Package：com.letus.common.utils
 * Author：xfyin
 * Date：2016-11-26 下午11:34:32
 * Copyright (C) 2016-2016 letus179-Copyright
 */
package com.letus.common.utils;

import java.util.Random;
import java.util.UUID;

/**
 * 工具类
 * 
 * @author xfyin
 * 
 */
public class IDUtil {
  
  /**
   * 去掉'-'的uuid字符串
   * 
   * @return String
   */
  public static String UUID() {
    return UUID.randomUUID().toString().replace("-", "");
  }
  
  /**
   * 納秒+3位随机数 生成图片的名称
   * 
   * @return 新图片名称
   */
  public static String genImageName() {
    long millis = System.nanoTime();
    Random ran = new Random();
    int end3 = ran.nextInt(999);
    return millis + String.valueOf(end3);
  }
  
  /**
   * 毫秒+2位随机数生成商品id
   * 
   * @return long商品id
   */
  public static long genItemId() {
    long millis = System.currentTimeMillis();
    Random ran = new Random();
    int end3 = ran.nextInt(99);
    return new Long(millis + String.valueOf(end3));
  }
  
}
