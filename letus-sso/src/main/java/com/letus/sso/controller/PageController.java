/**
 * File：PageController.java
 * Package：com.letus.sso.controller
 * Author：xfyin
 * Date：2016-12-15 下午10:06:00
 * Copyright (C) 2016-2016 letus179-Copyright
 */
package com.letus.sso.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 页面跳转controller
 * 
 * @author xfyin
 * 
 */
@Controller
@RequestMapping("/page")
public class PageController {
  
  /**
   * 跳转到注册页面
   * 
   * @return 注册页面
   */
  @RequestMapping("/register")
  public String register() {
    return "register";
  }
  
  /**
   * 跳转到登录页面
   * 
   * @param redirect
   *        登入成功后需要跳转的页面
   * @param model
   *        model
   * @return 登录页面
   */
  @RequestMapping("/login")
  public String login(String redirect, Model model) {
    model.addAttribute("redirect", redirect);
    return "login";
  }
  
}
