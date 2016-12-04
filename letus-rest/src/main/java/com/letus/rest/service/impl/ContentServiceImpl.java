/**
 * File：ContentServiceImpl.java
 * Package：com.letus.rest.service.impl
 * Author：xfyin
 * Date：2016-12-4 下午8:54:45
 * Copyright (C) 2016-2016 letus179-Copyright
 */
package com.letus.rest.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.letus.mapper.TbContentMapper;
import com.letus.pojo.TbContent;
import com.letus.pojo.TbContentExample;
import com.letus.pojo.TbContentExample.Criteria;
import com.letus.rest.service.ContentService;

/**
 * 首页展示商品内容信息service实现
 * 
 * @author xfyin
 * 
 */
@Service
public class ContentServiceImpl implements ContentService {
  
  /**
   * 注入 contentMapper
   */
  @Autowired
  private TbContentMapper contentMapper;
  
  @Override
  public List<TbContent> queryContentList(long categoryId) {
    TbContentExample example = new TbContentExample();
    Criteria criteria = example.createCriteria();
    criteria.andCategoryIdEqualTo(categoryId);
    return contentMapper.selectByExample(example);
  }
  
}
