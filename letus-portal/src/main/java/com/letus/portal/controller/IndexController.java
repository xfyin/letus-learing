/**
 * File：IndexController.java
 * Package：com.letus.portal.controller
 * Author：xfyin
 * Date：2016-12-1 下午11:29:45
 * Copyright (C) 2016-2016 letus179-Copyright
 */
package com.letus.portal.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 展示首页
 * 
 * @author xfyin
 * 
 */
@Controller
public class IndexController {
  
  /**
   * 页面URL：http://localhost:8082就能调用该方法的原因：
   * 因为在web.xml中配置了欢迎页：index.html;即url相当于：
   * http://localhost:8082/index.xml而在web.xml中
   * 配置了请求的拦截规则*.html，springmvc会在controller中
   * 找/index请求映射
   * 
   * @return 首页
   */
  @RequestMapping("/index")
  public String showIndex() {
    return "index";
  }
}
