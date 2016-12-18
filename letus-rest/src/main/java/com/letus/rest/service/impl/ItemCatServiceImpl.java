/**
 * File：ItemCatServiceImpl.java
 * Package：com.letus.rest.service.impl
 * Author：xfyin
 * Date：2016-12-3 下午2:02:11
 * Copyright (C) 2016-2016 letus179-Copyright
 */
package com.letus.rest.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.letus.common.utils.JsonUtils;
import com.letus.mapper.TbItemCatMapper;
import com.letus.pojo.TbItemCat;
import com.letus.pojo.TbItemCatExample;
import com.letus.pojo.TbItemCatExample.Criteria;
import com.letus.rest.dao.JedisClient;
import com.letus.rest.pojo.CatNode;
import com.letus.rest.pojo.CatResult;
import com.letus.rest.service.ItemCatService;

/**
 * 商品分类展示实现
 * 
 * @author xfyin
 * 
 */
@Service
public class ItemCatServiceImpl implements ItemCatService {
  
  /**
   * 首页商品类目信息在redis总保存的key
   */
  @Value("${INDEX_ITEM_CATEGROY_REDIS_KEY}")
  private String INDEX_ITEM_CATEGROY_REDIS_KEY;
  
  /**
   * jedis单机版
   */
  @Autowired
  private JedisClient jedisClientSingle;
  
  /**
   * 注入商品分类代理
   */
  @Autowired
  private TbItemCatMapper itemCatMapper;
  
  @Override
  public CatResult queryItemCatList() {
    // 分类列表
    return new CatResult(queryCatList(0));
  }
  
  /**
   * 查询分类列表
   * 
   * @param 父节点
   * @return catNodes
   */
  @SuppressWarnings({ "unchecked", "rawtypes" })
  private List<?> queryCatList(long parentId) {
    
    // 从redis缓存中取
    try {
      String getcatNodes = jedisClientSingle.hget(INDEX_ITEM_CATEGROY_REDIS_KEY, parentId + "");
      if(!StringUtils.isEmpty(getcatNodes)) {
        return JsonUtils.jsonToList(getcatNodes, Object.class);
      }
    }
    catch (Exception e) {
     e.printStackTrace();
    }
    
    // 若缓存中没有，则从数据库中取
    // 构造查询条件
    TbItemCatExample example = new TbItemCatExample();
    Criteria criteria = example.createCriteria();
    criteria.andParentIdEqualTo(parentId);
    // 执行查询，将父节点对应的数据取出
    List<TbItemCat> itemCats = itemCatMapper.selectByExample(example);
    // 需要返回的分类节点
    List catNodes = new ArrayList<>();
    
    int count = 0;
    // 向catNodes中添加节点
    for (TbItemCat cat : itemCats) {
      
      // 该节点是父节点
      if (cat.getIsParent()) {
        
        CatNode node = new CatNode();
        node.setUrl("/products/" + cat.getId() + ".html");
        if (parentId == 0) {
          node.setName("<a href='/products/" + cat.getId() + ".html'>" + cat.getName() + "</a>");
        }
        else {
          node.setName(cat.getName());
        }
        // 递归调用
        node.setItems(queryCatList(cat.getId()));
        
        catNodes.add(node);
        count ++;
        // 第一层只取14条记录
        if (parentId == 0 && count >= 14) {
          break;
        }
      }
      // 叶子节点的时候只有一层
      else {
        catNodes.add("/products/" + cat.getId() + ".html|" + cat.getName());
      }
    }
    
    // 将商品类目信息保存到redis中缓存
    try {
      String catNodesCache = JsonUtils.objectToJson(catNodes);
      jedisClientSingle.hset(INDEX_ITEM_CATEGROY_REDIS_KEY, parentId + "", catNodesCache);
    }
    catch (Exception e) {
      e.printStackTrace();
    }
    
    
    
    return catNodes;
  }
  
}
