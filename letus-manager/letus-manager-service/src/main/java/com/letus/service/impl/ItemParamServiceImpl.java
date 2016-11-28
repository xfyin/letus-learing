/**
 * File：ItemParamServiceImpl.java
 * Package：com.letus.service.impl
 * Author：xfyin
 * Date：2016-11-28 下午11:58:48
 * Copyright (C) 2016-2016 letus179-Copyright
 */
package com.letus.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.letus.common.pojo.LetusResult;
import com.letus.mapper.TbItemParamMapper;
import com.letus.pojo.TbItemParam;
import com.letus.pojo.TbItemParamExample;
import com.letus.pojo.TbItemParamExample.Criteria;
import com.letus.service.ItemParamService;


/**
 * 商品规格参数模板service实现
 * 
 * @author xfyin
 *
 */
@Service
public class ItemParamServiceImpl implements ItemParamService {
  
  /**
   * 注入代理对象
   */
  @Autowired
  private TbItemParamMapper itemParamMapper;
  
  @Override
  public LetusResult getItemParamByCid(long cid) {
    TbItemParamExample example = new TbItemParamExample();
    Criteria criteria = example.createCriteria();
    criteria.andItemCatIdEqualTo(cid);
    List<TbItemParam> list = itemParamMapper.selectByExample(example);
    // 判断查询结果
    if (list == null || list.size() == 0) {
      return LetusResult.ok();
    } 
    return LetusResult.ok(list.get(0));
  }
  
}
