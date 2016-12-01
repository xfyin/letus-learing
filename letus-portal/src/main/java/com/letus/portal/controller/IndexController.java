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
  
  @RequestMapping("/index")
  public String showIndex() {
    return "index";
  }
}
