/**
 * File：ContentServiceImpl.java
 * Package：com.letus.service.impl
 * Author：xfyin
 * Date：2016-12-4 下午7:13:18
 * Copyright (C) 2016-2016 letus179-Copyright
 */
package com.letus.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.letus.common.pojo.EUDataGridResult;
import com.letus.common.pojo.LetusResult;
import com.letus.common.utils.HttpClientUtil;
import com.letus.mapper.TbContentMapper;
import com.letus.pojo.TbContent;
import com.letus.pojo.TbContentExample;
import com.letus.pojo.TbContentExample.Criteria;
import com.letus.service.ContentService;

/**
 * 内容管理实现
 * 
 * @author xfyin
 * 
 */
@Service
public class ContentServiceImpl implements ContentService {
  
  @Value("${REST_BASE_URL}")
  private String REST_BASE_URL;
  
  @Value("${REST_CONTENT_SYNC_URL}")
  private String REST_CONTENT_SYNC_URL;
  
  /**
   * 注入contentMapper
   */
  @Autowired
  private TbContentMapper contentMapper;
  
  @Override
  public EUDataGridResult queryContentList(int page, int rows, long categoryId) {
    // 参考 com.letus.service.impl.ItemServiceImpl.getItemList(int, int)
    // 分页
    PageHelper.startPage(page, rows);
    // 获取列表信息
    TbContentExample example = new TbContentExample();
    Criteria criteria = example.createCriteria();
    criteria.andCategoryIdEqualTo(categoryId);
    List<TbContent> contents = contentMapper.selectByExample(example);
    // 获取页面信息
    PageInfo<TbContent> info = new PageInfo<>(contents);
    // 总记录数和列表信息返回
    return new EUDataGridResult(info.getTotal(), contents);
  }
  
  @Override
  public LetusResult addContent(TbContent content) {
    content.setCreated(new Date());
    content.setUpdated(new Date());
    contentMapper.insert(content);
    
    // 添加缓存同步逻辑, 删除原来的key对应的缓存内容
    try {
      HttpClientUtil.doGet(REST_BASE_URL + REST_CONTENT_SYNC_URL + content.getCategoryId());
    }
    catch (Exception e) {
      e.printStackTrace();
    }
    
    return LetusResult.ok();
  }
  
  @Override
  public LetusResult modifyContent(TbContent content) {
    
    // 需要修改的商品内容，取出创建时间
    TbContent oldContent = queryContent(content.getId());
    content.setCreated(oldContent.getCreated());
    
    content.setUpdated(new Date());
    contentMapper.updateByPrimaryKey(content);

    // 添加缓存同步逻辑, 删除原来的key对应的缓存内容
    try {
      HttpClientUtil.doGet(REST_BASE_URL + REST_CONTENT_SYNC_URL + content.getCategoryId());
    }
    catch (Exception e) {
      e.printStackTrace();
    }
    
    return LetusResult.ok();
  }
  
  @Override
  public TbContent queryContent(long id) {
    return contentMapper.selectByPrimaryKey(id);
  }
  
  @Override
  public void deleteContent(long id) {
    contentMapper.deleteByPrimaryKey(id);
  }
  
}
