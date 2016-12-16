/**
 * File：UserServiceImpl.java
 * Package：com.letus.sso.service.impl
 * Author：xfyin
 * Date：2016-12-14 下午8:56:23
 * Copyright (C) 2016-2016 letus179-Copyright
 */
package com.letus.sso.service.impl;

import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

import com.letus.common.pojo.LetusResult;
import com.letus.common.utils.IDUtil;
import com.letus.common.utils.JsonUtils;
import com.letus.mapper.TbUserMapper;
import com.letus.pojo.TbUser;
import com.letus.pojo.TbUserExample;
import com.letus.pojo.TbUserExample.Criteria;
import com.letus.sso.dao.JedisClient;
import com.letus.sso.service.UserService;

/**
 * 
 * 
 * @author xfyin
 * 
 */
@Service
public class UserServiceImpl implements UserService {
  
  /**
   * 用户session在redis中保存的key
   */
  @Value("${REDIS_USER_SESSION_KEY}")
  private String REDIS_USER_SESSION_KEY;
  
  /**
   * 设置Session的过期时间 30分钟
   */
  @Value("${SSO_SESSION_EXPIRE}")
  private Integer SSO_SESSION_EXPIRE;
  
  /**
   * 门户portal系统的首页URL
   */
  @Value("${PORTAL_BASE_URL}")
  private String PORTAL_BASE_URL;
  
  /**
   * 缓存
   */
  @Autowired
  private JedisClient jedisClient;
  
  /**
   * 注入mapper
   */
  @Autowired
  private TbUserMapper userMapper;
  
  @Override
  public LetusResult checkUserInfo(String content, int type) {
    TbUserExample example = new TbUserExample();
    Criteria criteria = example.createCriteria();
    // 用户名校验
    if (1 == type) {
      criteria.andUsernameEqualTo(content);
    }
    // 手机号校验
    else if (2 == type) {
      criteria.andPhoneEqualTo(content);
    }
    // 邮箱校验
    else {
      criteria.andEmailEqualTo(content);
    }
    List<TbUser> users = userMapper.selectByExample(example);
    // 用户不存在,数据可用
    if (users == null || users.size() == 0) {
      return LetusResult.ok(true);
    }
    return LetusResult.ok(false);
  }
  
  @Override
  public LetusResult addUser(TbUser user) {
    user.setCreated(new Date());
    user.setUpdated(new Date());
    // 对密码进行md5加密，直接调用spring框架封装的工具类
    user.setPassword(DigestUtils.md5DigestAsHex(user.getPassword().getBytes()));
    int result = userMapper.insert(user);
    if (1 == result) {
      return LetusResult.ok();
    }
    return LetusResult.build(104, "注册失败");
  }
  
  @Override
  public LetusResult queryUserByParam(String param, int type) {
    TbUserExample example = new TbUserExample();
    Criteria criteria = example.createCriteria();
    // 用户名
    if (1 == type) {
      criteria.andUsernameEqualTo(param);
      List<TbUser> users = userMapper.selectByExample(example);
      if (users == null || users.size() == 0) {
        return LetusResult.build(103, "用户名不存在");
      }
      return LetusResult.build(200, "用户名已存在", users.get(0));
    }
    // 手机号
    else if (2 == type) {
      criteria.andPhoneEqualTo(param);
      List<TbUser> users = userMapper.selectByExample(example);
      if (users == null || users.size() == 0) {
        return LetusResult.build(103, "手机号不存在");
      }
      return LetusResult.build(200, "手机号已存在", users.get(0));
    }
    // 邮箱
    else {
      criteria.andEmailEqualTo(param);
      List<TbUser> users = userMapper.selectByExample(example);
      if (users == null || users.size() == 0) {
        return LetusResult.build(103, "邮箱地址不存在");
      }
      return LetusResult.build(200, "邮箱地址已存在", users.get(0));
    }
  }
  
  @Override
  public LetusResult login(String username, String password) {
    LetusResult result = this.queryUserByParam(username, 1);
    // 用户不存在
    if (103 == result.getStatus()) {
      return LetusResult.build(103, "用户名或密码错误");
    }
    // 用户
    TbUser user = (TbUser) result.getData();
    // 密码错误
    if (!DigestUtils.md5DigestAsHex(password.getBytes()).equals(user.getPassword())) {
      return LetusResult.build(103, "用户名或密码错误");
    }
    // token
    String token = IDUtil.UUID();
    // 保存到缓存中,用户信息中的密码需要去掉
    user.setPassword(null);
    String key = REDIS_USER_SESSION_KEY + ":" + token;
    jedisClient.set(key, JsonUtils.objectToJson(user));
    // 设置session的过期时间
    jedisClient.expire(key, SSO_SESSION_EXPIRE);
    return LetusResult.ok(token);
  }
  
  @Override
  public LetusResult queryUserByToken(String token) {
    String key = REDIS_USER_SESSION_KEY + ":" + token;
    String jsonUser = jedisClient.get(key);
    // session信息是否有效
    if (StringUtils.isBlank(jsonUser)) {
      return LetusResult.build(104, "SESSION已过期，请重新登录");
    }
    // 重新设置过期时间
    jedisClient.expire(key, SSO_SESSION_EXPIRE);
    return LetusResult.ok(JsonUtils.jsonToPojo(jsonUser, TbUser.class));
  }

  @Override
  public LetusResult logout(String token) {
    jedisClient.del(REDIS_USER_SESSION_KEY + ":" + token);
    return LetusResult.ok(PORTAL_BASE_URL);
  }
}
