/**
 * File：ExceptionUtil.java
 * Package：com.letus.common.utils
 * Author：xfyin
 * Date：2016-12-4 下午9:09:33
 * Copyright (C) 2016-2016 letus179-Copyright
 */
package com.letus.common.utils;

import java.io.PrintWriter;
import java.io.StringWriter;

/**
 * 异常工具类
 * 
 * @author xfyin
 * 
 */
public class ExceptionUtil {
  
  /**
   * 获取异常的堆栈信息
   * 
   * @param t
   *        异常
   * @return 堆栈信息
   */
  public static String getStackTrace(Throwable t) {
    StringWriter sw = new StringWriter();
    PrintWriter pw = new PrintWriter(sw);
    try {
      t.printStackTrace(pw);
      return sw.toString();
    }
    finally {
      pw.close();
    }
  }
}
