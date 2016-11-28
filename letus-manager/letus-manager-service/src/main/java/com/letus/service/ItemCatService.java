/**
 * File：ItemCatService.java
 * Package：com.letus.service
 * Author：xfyin
 * Date：2016-11-26 下午2:28:19
 * Copyright (C) 2016-2016 letus179-Copyright
 */
package com.letus.service;

import java.util.List;

import com.letus.common.pojo.EUTreeNode;

/**
 * 商品类目信息service
 * 
 * @author xfyin
 * 
 */
public interface ItemCatService {
  
  /**
   * 选择商品类目时展示树形节点
   * 
   * @param parentId
   *        父节点id
   * @return 节点列表
   */
  List<EUTreeNode> getItemCatList(long parentId);
}
