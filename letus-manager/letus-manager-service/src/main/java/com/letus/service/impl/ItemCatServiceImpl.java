/**
 * File：ItemCatServiceImpl.java
 * Package：com.letus.service.impl
 * Author：xfyin
 * Date：2016-11-26 下午2:29:41
 * Copyright (C) 2016-2016 letus179-Copyright
 */
package com.letus.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.letus.common.pojo.EUTreeNode;
import com.letus.mapper.TbItemCatMapper;
import com.letus.pojo.TbItemCat;
import com.letus.pojo.TbItemCatExample;
import com.letus.pojo.TbItemCatExample.Criteria;
import com.letus.service.ItemCatService;

/**
 * 商品类目列表实现类
 * 
 * @author xfyin
 * 
 */
@Service
public class ItemCatServiceImpl implements ItemCatService {
  
  /**
   * 注入itemCatMapper代理
   */
  @Autowired
  private TbItemCatMapper itemCatMapper;
  
  @Override
  public List<EUTreeNode> getItemCatList(long parentId) {
    TbItemCatExample example = new TbItemCatExample();
    Criteria criteria = example.createCriteria();
    criteria.andParentIdEqualTo(parentId);
    List<TbItemCat> itemCats = itemCatMapper.selectByExample(example);
    List<EUTreeNode> treeNodes = new ArrayList<>();
    for (TbItemCat cat : itemCats) {
      EUTreeNode node = new EUTreeNode();
      node.setId(cat.getId());
      node.setText(cat.getName());
      // 如果是父节点就设置为关闭状态，如果是叶子节点就设置为打开状态，true表示是父类目
      node.setState(cat.getIsParent() ? "closed" : "open");
      treeNodes.add(node);
    }
    return treeNodes;
  }
  
}
