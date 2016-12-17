/**
 * File：UserController.java
 * Package：com.letus.sso.controller
 * Author：xfyin
 * Date：2016-12-14 下午9:21:18
 * Copyright (C) 2016-2016 letus179-Copyright
 */
package com.letus.sso.controller;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.letus.common.pojo.LetusResult;
import com.letus.common.utils.CookieUtils;
import com.letus.common.utils.ExceptionUtil;
import com.letus.pojo.TbUser;
import com.letus.sso.service.UserService;
import com.letus.sso.service.impl.UserServiceImpl;

/**
 * 用户controller
 * 
 * @author xfyin
 * 
 */
@Controller
@RequestMapping("/user")
public class UserController {
  
  /**
   * 注入service
   */
  @Autowired
  private UserService userService;
  
  /**
   * 用户注册前，检查用户信息
   * 
   * @param content
   *          内容
   * @param type
   *          类型
   * @param callback
   *          回调函数
   * @return Object
   */
  @RequestMapping("/check/{content}/{type}")
  @ResponseBody
  public Object checkUserInfo(@PathVariable String content, @PathVariable int type, String callback) {
    
    LetusResult result = null;
    
    // 参数有效性校验
    if (StringUtils.isBlank(content)) {
      result = LetusResult.build(103, "校验内容不能为空", false);
    }
    // 参数类型有效性校验 type只能在{1,2,3}中
    else if (type != 1 && type != 2 && type != 3) {
      result = LetusResult.build(103, "校验内容类型不在{1,2,3}中", false);
    }
    
    // 校验的参数不符合要求时
    Object checkResult = this.checkResult(result, callback);
    if (checkResult != null) {
      return checkResult;
    }
    
    // 业务逻辑处理
    try {
      result = userService.checkUserInfo(content, type);
    }
    catch (Exception e) {
      e.printStackTrace();
      result = LetusResult.build(104, ExceptionUtil.getStackTrace(e), false);
    }
    
    return this.checkResult(result, callback);
    
  }
  
  /**
   * 注册时 增加用户
   * 
   * @param user
   *          用户信息
   * @return LetusResult
   */
  @RequestMapping(value = "/register", method = RequestMethod.POST)
  @ResponseBody
  public LetusResult addUser(TbUser user) {
    // 校验用户名，不能重复,不能为空
    LetusResult result = userService.queryUserByParam(user.getUsername(), 1);
    if (result.getStatus() == 200) {
      result.setData(null);
      return result;
    }
    // 校验手机号，不能重复，可以为空
    String phone = user.getPhone();
    if (!StringUtils.isBlank(phone)) {
      result = userService.queryUserByParam(phone, 2);
      if (result.getStatus() == 200) {
        result.setData(null);
        return result;
      }
    }
    // 校验邮箱，不能重复，可以为空
    String email = user.getEmail();
    if (!StringUtils.isBlank(email)) {
      result = userService.queryUserByParam(user.getEmail(), 3);
      if (result.getStatus() == 200) {
        result.setData(null);
        return result;
      }
    }
    
    try {
      return userService.addUser(user);
    }
    catch (Exception e) {
      e.printStackTrace();
      return LetusResult.build(104, ExceptionUtil.getStackTrace(e));
    }
  }
  
  /**
   * 用户登录
   * 
   * @param request
   *          请求
   * @param response
   *          响应
   * @param username
   *          用户名
   * @param password
   *          密码
   * @return LetusResult
   */
  @RequestMapping(value = "/login", method = RequestMethod.POST)
  @ResponseBody
  public LetusResult login(HttpServletRequest request, HttpServletResponse response,
                           String username, String password) {
    // 用户名，密码 非空校验
    if (StringUtils.isBlank(username)) {
      return LetusResult.build(103, "用户名不能为空");
    }
    if (StringUtils.isBlank(password)) {
      return LetusResult.build(103, "密码不能为空");
    }
    
    try {
      // 登录逻辑处理
      LetusResult result = userService.login(username, password);
      // 登录成功后回去token
      String token = (String) result.getData();
      // 设置cookie，其中cookie的有效期为关闭浏览器时为止
      CookieUtils
          .setCookie(request, response, new UserServiceImpl().LOGIN_TOKEN_COOKIE_NAME, token);
      return result;
    }
    catch (Exception e) {
      e.printStackTrace();
      return LetusResult.build(104, ExceptionUtil.getStackTrace(e));
    }
  }
  
  /**
   * 根据token获取用户信息
   * 
   * @param token
   *          token
   * @param callback
   *          回调函数
   * @return LetusResult
   */
  @RequestMapping(value = "/token/{token}", method = RequestMethod.GET)
  @ResponseBody
  public Object queryUserByToken(@PathVariable String token, String callback) {
    LetusResult result = null;
    try {
      result = userService.queryUserByToken(token);
    }
    catch (Exception e) {
      e.printStackTrace();
      result = LetusResult.build(104, ExceptionUtil.getStackTrace(e));
    }
    
    if (StringUtils.isBlank(callback)) {
      return result;
    }
    // jsonp远程调用 返回回调函数
    MappingJacksonValue value = new MappingJacksonValue(result);
    value.setJsonpFunction(callback);
    return value;
  }
  
  /**
   * 安全退出登录
   * 
   * @param token
   *          token
   * @param callback
   *          回调函数
   * @return Object
   */
  @RequestMapping(value = "/logout/{token}", method = RequestMethod.GET)
  @ResponseBody
  public Object logout(@PathVariable String token, String callback, HttpServletResponse response) {
    LetusResult result = userService.logout(token);
    if (StringUtils.isBlank(callback)) {
      try {
        // 回调首页
        response.sendRedirect((String) result.getData());
      }
      catch (IOException e) {
        e.printStackTrace();
      }
      return null;
    }
    MappingJacksonValue value = new MappingJacksonValue(result);
    value.setJsonpFunction(callback);
    return value;
  }
  
  /**
   * 校验结果返回
   * 
   * @param result
   *          校验结果
   * @param callback
   *          回调函数
   * @return Object
   */
  private Object checkResult(LetusResult result, String callback) {
    if (null != result) {
      // 回调函数存在，用jsonp跨域处理
      if (!StringUtils.isBlank(callback)) {
        MappingJacksonValue value = new MappingJacksonValue(result);
        value.setJsonpFunction(callback);
        return value;
      }
    }
    return result;
  }
  
}
