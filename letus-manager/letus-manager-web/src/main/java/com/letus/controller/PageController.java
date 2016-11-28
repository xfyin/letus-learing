/**
 * File：PageController.java
 * Package：com.letus.controller
 * Author：xfyin
 * Date：2016-11-26 上午11:35:39
 * Copyright (C) 2016-2016 letus179-Copyright
 */
package com.letus.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 跳转首页controller
 * 
 * @author xfyin
 * 
 */
@Controller
public class PageController {
  
  /**
   * 打开首页
   * 
   * @return 首页
   */
  @RequestMapping(value="/")
  public String showIndex() {
    return "index";
  }
  
  /**
   * 打开其他页面
   * @param page 需要访问的页码
   * @return 打开页面
   */
  @RequestMapping(value="/{page}")
  public String showPage(@PathVariable String page) {
    return page;
  }
}
