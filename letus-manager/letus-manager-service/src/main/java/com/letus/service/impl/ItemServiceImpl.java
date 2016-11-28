/**
 * File：ItemServiceImpl.java
 * Package：com.letus.service.impl
 * Author：xfyin
 * Date：2016-11-24 下午9:29:33
 * Copyright (C) 2016-2016 letus179-Copyright
 */
package com.letus.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.letus.common.pojo.EUDataGridResult;
import com.letus.common.pojo.LetusResult;
import com.letus.common.utils.IDUtil;
import com.letus.mapper.TbItemDescMapper;
import com.letus.mapper.TbItemMapper;
import com.letus.pojo.TbItem;
import com.letus.pojo.TbItemDesc;
import com.letus.pojo.TbItemExample;
import com.letus.pojo.TbItemExample.Criteria;
import com.letus.service.ItemService;

/**
 * 实现类
 * 
 * @author xfyin
 */
@Service
public class ItemServiceImpl implements ItemService {
  
  /**
   * 商品信息mapper
   */
  @Autowired
  private TbItemMapper itemMapper;
  
  /*
   * 商品描述信息mapper
   */
  @Autowired
  private TbItemDescMapper itemDescMapper;
  
  @Override
  public TbItem getItemById(long itemId) {
    // TbItem selectByPrimaryKey = itemMapper.selectByPrimaryKey(itemId);
    
    TbItemExample example = new TbItemExample();
    Criteria criteria = example.createCriteria();
    criteria.andIdEqualTo(itemId);
    List<TbItem> items = itemMapper.selectByExample(example);
    if (items != null && items.size() != 0) {
      return items.get(0);
    }
    return null;
  }
  
  @Override
  public EUDataGridResult getItemList(int page, int rows) {
    // 分页信息 在执行查询sql之前调用
    PageHelper.startPage(page, rows);
    // 商品信息
    TbItemExample example = new TbItemExample();
    List<TbItem> items = itemMapper.selectByExample(example);
    // 页面信息
    PageInfo<TbItem> total = new PageInfo<>(items);
    // 返回记录总数和记录list信息
    return new EUDataGridResult(total.getTotal(), items);
  }
  
  @Override
  public LetusResult createItemAndItemDesc(TbItem item, String desc) {
    // 补全 item信息
    long itemId = IDUtil.genItemId();
    item.setId(itemId);
    // 商品状态，1-正常，2-下架，3-删除
    item.setStatus((byte) 1);
    item.setCreated(new Date());
    item.setUpdated(new Date());
    itemMapper.insert(item);
    // 添加商品描述信息
    createItemDesc(itemId, desc);
    return LetusResult.ok();
  }
  
  /**
   * 添加商品信息描述
   * 
   * @param itemId
   *        主键
   * @param desc
   *        描述信息
   * @return 响应结果
   */
  private LetusResult createItemDesc(long itemId, String desc) {
    TbItemDesc itemDesc = new TbItemDesc();
    itemDesc.setItemId(itemId);
    itemDesc.setItemDesc(desc);
    itemDesc.setCreated(new Date());
    itemDesc.setUpdated(new Date());
    itemDescMapper.insert(itemDesc);
    return LetusResult.ok();
  }
}
