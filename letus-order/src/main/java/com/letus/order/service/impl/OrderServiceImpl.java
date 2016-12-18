/**
 * File：OrderServiceImpl.java
 * Package：com.letus.order.service.impl
 * Author：xfyin
 * Date：2016-12-18 下午3:53:47
 * Copyright (C) 2016-2016 letus179-Copyright
 */
package com.letus.order.service.impl;

import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.letus.common.pojo.LetusResult;
import com.letus.mapper.TbOrderItemMapper;
import com.letus.mapper.TbOrderMapper;
import com.letus.mapper.TbOrderShippingMapper;
import com.letus.order.dao.JedisClient;
import com.letus.order.service.OrderService;
import com.letus.pojo.TbOrder;
import com.letus.pojo.TbOrderItem;
import com.letus.pojo.TbOrderShipping;

/**
 * 订单管理service实现
 * 
 * @author xfyin
 * 
 */
@Service
public class OrderServiceImpl implements OrderService {
  
  /**
   * 订单mapper
   */
  @Autowired
  private TbOrderMapper orderMapper;
  
  /**
   * 订单详情mapper
   */
  @Autowired
  private TbOrderItemMapper orderItemMapper;
  
  /**
   * 物流信息mapper
   */
  @Autowired
  private TbOrderShippingMapper orderShippingMapper;
  
  /**
   * jedis
   */
  @Autowired
  private JedisClient jedisClient;
  
  /**
   * 订单号生成key
   */
  @Value("${ORDER_GEN_KEY}")
  private String ORDER_GEN_KEY;
  
  /**
   * 初始订单号
   */
  @Value("${ORDER_INIT_ID}")
  private String ORDER_INIT_ID;
  
  /**
   * 订单明细生成key
   */
  @Value("${ORDER_DETAIL_GEN_KEY}")
  private String ORDER_DETAIL_GEN_KEY;
  
  
  @Override
  public LetusResult createOrder(TbOrder order, List<TbOrderItem> orderItems,
                                 TbOrderShipping orderShipping) {
    // 1.插入订单
    // 获取一个易读，且不会重复的订单号，从redis中获取并补全信息
    // 先看看redis中这个key有值否，没有的话就设置一些初始值
    String value = jedisClient.get(ORDER_GEN_KEY);
    if (StringUtils.isBlank(value)) {
      jedisClient.set(ORDER_GEN_KEY, ORDER_INIT_ID);
    }
    // redis自增长值作为订单号
    String orderId = jedisClient.incr(ORDER_GEN_KEY) + "";
     
    order.setOrderId(orderId);
    // 状态：1、未付款，2、已付款，3、未发货，4、已发货，5、交易成功，6、交易关闭',
    order.setStatus(1);
    //当前时间
    Date curDate = new Date();
    order.setCreateTime(curDate);
    order.setUpdateTime(curDate);
    // 0：未评价；1：已评价 
    order.setBuyerRate(0);
    orderMapper.insert(order);
    
    // 2.插入订单明细
    for (TbOrderItem item : orderItems) {
      // 订单明细主键
      long orderDetailId= jedisClient.incr(ORDER_DETAIL_GEN_KEY);
      item.setId(orderDetailId + "");
      item.setOrderId(orderId);
      orderItemMapper.insert(item);
    }
    // 3.插入物流信息
    orderShipping.setOrderId(orderId);
    orderShipping.setCreated(curDate);
    orderShipping.setUpdated(curDate);
    orderShippingMapper.insert(orderShipping);
    return LetusResult.ok(orderId);
  }
}
