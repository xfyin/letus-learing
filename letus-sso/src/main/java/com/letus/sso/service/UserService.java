/**
 * File：UserService.java
 * Package：com.letus.sso.service
 * Author：xfyin
 * Date：2016-12-14 下午8:53:43
 * Copyright (C) 2016-2016 letus179-Copyright
 */
package com.letus.sso.service;

import com.letus.common.pojo.LetusResult;
import com.letus.pojo.TbUser;

/**
 * 用户信息
 * 
 * @author xfyin
 * 
 */
public interface UserService {
  
  /**
   * 校验用户信息（注册用）
   * 
   * @param content
   *        内容：用户名/手机号/邮箱
   * @param type
   *        类型：1/2/3
   * @return LetusResult
   */
  LetusResult checkUserInfo(String content, int type);
  
  /**
   * 增加用户
   * 
   * @param user
   *        用户信息
   * @return LetusResult
   */
  LetusResult addUser(TbUser user);
  
  /**
   * 根据参数查询用户信息
   * 
   * @param param
   *        用户名/手机号/邮箱
   * @param type
   *        1/2/3
   * @return LetusResult
   */
  LetusResult queryUserByParam(String param, int type);
  
  /**
   * 用户登录
   * 
   * @param username
   *        用户名
   * @param password
   *        密码
   * @return LetusResult
   */
  LetusResult login(String username, String password);
  
  /**
   * 通过token从redis缓存中取用户信息
   * 
   * @param token
   *        token信息
   * @return LetusResult
   */
  LetusResult queryUserByToken(String token);
  
  /**
   * 安全退出
   * 
   * @param token
   *        token信息
   * @return LetusResult
   */
  LetusResult logout(String token);
  
}
