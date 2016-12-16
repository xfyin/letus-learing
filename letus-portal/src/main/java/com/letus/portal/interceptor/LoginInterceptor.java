/**
 * File：LoginInterceptor.java
 * Package：com.letus.portal.interceptor
 * Author：xfyin
 * Date：2016-12-16 下午10:04:31
 * Copyright (C) 2016-2016 letus179-Copyright
 */
package com.letus.portal.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.letus.common.utils.CookieUtils;
import com.letus.pojo.TbUser;
import com.letus.portal.service.impl.UserServiceImpl;

/**
 * 登录拦截器
 * 
 * @author xfyin
 * 
 */
public class LoginInterceptor implements HandlerInterceptor {
  
  /**
   * 注入用户service
   */
  @Autowired
  private UserServiceImpl userService;
  
  @Override
  public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
      throws Exception {
    // 在handle之前处理；返回值决定handle是否执行，true执行，false不执行
    // 1.判断用户是否登录
    // ---从cookie中取token
    String token = CookieUtils.getCookieValue(request, "LS_TOKEN");
    // ---根据token换取用户信息，调用sso系统中的接口
    TbUser user = userService.queryUserByToken(token);
    // 若取不到用户信息，则跳转到登录页面,并把用户请求的URL作为参数传递给登录页面
    if (user == null) {
      response.sendRedirect(userService.SSO_BASE_URL + userService.SSO_PAGE_URL + "?redirect="
          + request.getRequestURL());
      // 返回false
      return false;
    }
    // 若取到用户信息； 放行
    return true;
  }
  
  @Override
  public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
                         ModelAndView modelAndView) throws Exception {
    // 在handle之后，返回ModelAndView之前处理，
    
  }
  
  @Override
  public void afterCompletion(HttpServletRequest request, HttpServletResponse response,
                              Object handler, Exception ex) throws Exception {
    // 返回ModelAndView之后处理。响应用户后
    
  }
  
}
