/**
 * File：UserService.java
 * Package：com.letus.portal.service
 * Author：xfyin
 * Date：2016-12-16 下午10:18:55
 * Copyright (C) 2016-2016 letus179-Copyright
 */
package com.letus.portal.service;

import com.letus.pojo.TbUser;

/**
 * 用户管理service
 * 
 * @author xfyin
 * 
 */
public interface UserService {
  
  /**
   * 根据token获取用户信息
   * 
   * @param token
   *          token
   * @return 用户信息
   */
  TbUser queryUserByToken(String token);
}
