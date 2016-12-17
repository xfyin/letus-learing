/**
 * File：UserServiceImpl.java
 * Package：com.letus.portal.service.impl
 * Author：xfyin
 * Date：2016-12-16 下午10:19:48
 * Copyright (C) 2016-2016 letus179-Copyright
 */
package com.letus.portal.service.impl;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.letus.common.pojo.LetusResult;
import com.letus.common.utils.HttpClientUtil;
import com.letus.pojo.TbUser;
import com.letus.portal.service.UserService;

/**
 * 用户管理service实现
 * 
 * @author xfyin
 * 
 */
@Service
public class UserServiceImpl implements UserService {
  
  /**
   * 单点登录系统的URL
   */
  @Value("${SSO_BASE_URL}")
  public String SSO_BASE_URL;
  
  /**
   * 根据token取用户信息
   */
  @Value("${SSO_USER_TOKEN}")
  private String SSO_USER_TOKEN;
  
  /**
   * 单点登录系统的登录URL
   */
  @Value("${SSO_PAGE_URL}")
  public String SSO_PAGE_URL;
  
  /**
   * 用户登录token的cookie名称
   */
  @Value("${LOGIN_TOKEN_COOKIE_NAME}")
  public String LOGIN_TOKEN_COOKIE_NAME;
  
  @Override
  public TbUser queryUserByToken(String token) {
    try {
      // 调用sso系统，通过token获取用户信息
      String jsonUser = HttpClientUtil.doGet(SSO_BASE_URL + SSO_USER_TOKEN + token);
      // 将jsonUser转换为data为TbUser的LetusResult
      LetusResult result = LetusResult.formatToPojo(jsonUser, TbUser.class);
      if (result.getStatus() == 200) {
        return (TbUser) result.getData();
      }
    }
    catch (Exception e) {
      e.printStackTrace();
    }
    return null;
  }
  
}
