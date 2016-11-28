/**
 * File：TestPageHelper.java
 * Package：com.letus.controller
 * Author：xfyin
 * Date：2016-11-26 下午12:26:53
 * Copyright (C) 2016-2016 letus179-Copyright
 */
package com.letus.controller;

import java.util.List;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.letus.mapper.TbItemMapper;
import com.letus.pojo.TbItem;
import com.letus.pojo.TbItemExample;
import com.letus.pojo.TbItemExample.Criteria;


/**
 * 分页查询测试
 * 
 * @author xfyin
 *
 */
public class TestPageHelper {
  
  @Test
  public void testPageHelper() {
    // 创建spring容器
    ApplicationContext context = new ClassPathXmlApplicationContext("classpath:spring/applicationContext-*.xml");
    //从spring容器中获取mapper代理对象
    TbItemMapper bean = context.getBean(TbItemMapper.class);
    //调用查询方法，并执行分页
    TbItemExample example = new TbItemExample();
    // 分页处理
    PageHelper.startPage(1, 20);
    List<TbItem> items = bean.selectByExample(example);
    // 取商品信息
    for (TbItem item : items) {
      System.out.println(item.getTitle());
    }
    // 取分页信息
    PageInfo<TbItem> info = new PageInfo<>(items);
    long total = info.getTotal();
    System.out.println("共有商品信息："+total);
  }
}
