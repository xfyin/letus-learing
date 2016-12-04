/**
 * File：IndexController.java
 * Package：com.letus.portal.controller
 * Author：xfyin
 * Date：2016-12-1 下午11:29:45
 * Copyright (C) 2016-2016 letus179-Copyright
 */
package com.letus.portal.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.letus.portal.service.ContentService;

/**
 * 展示首页
 * 
 * @author xfyin
 * 
 */
@Controller
public class IndexController {
  
  /**
   * 注入service
   */
  @Autowired
  private ContentService contentService;
  
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
  public ModelAndView showIndex(ModelAndView mv) {
    String adJson = contentService.queryContentList();
    mv.addObject("ad1", adJson);
    mv.setViewName("index");
    return mv;
  }
  
  /**
   * 测试httpClient的post请求
   * 
   * @return LetusResult
   */
  @RequestMapping(value = "/httpclient/post", method = RequestMethod.POST)
  @ResponseBody
  public String httpClientTest() {
    return "OK";
  }
  
  /**
   * 测试httpClient的带参数的post请求
   * 
   * @return LetusResult
   */
  @RequestMapping(value = "/httpclient/postwithparams", method = RequestMethod.POST)
  @ResponseBody
  public String httpClientWithParamTest(String name, String pwd) {
    return "name:" + name + "\tpassword:" + pwd;
  }
}
