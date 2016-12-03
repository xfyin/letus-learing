/**
 * File：ContentCatgoryService.java
 * Package：com.letus.service
 * Author：xfyin
 * Date：2016-12-3 下午4:18:27
 * Copyright (C) 2016-2016 letus179-Copyright
 */
package com.letus.service;

import java.util.List;

import com.letus.common.pojo.EUTreeNode;
import com.letus.common.pojo.LetusResult;

/**
 * 后台管理--内容分类CMS系统service
 * 
 * @author xfyin
 * 
 */
public interface ContentCatgoryService {
  
  /**
   * 根据parentId获取内容分类列表
   * 
   * @param parentId
   *        父节点
   * @return 异步Tree格式
   */
  List<EUTreeNode> queryCatgoryList(long parentId);
  
  /**
   * 增加商品分类记录
   * 
   * @param parentId
   *        该记录的父节点id
   * @param name
   *        该记录的名称
   * @return LetusResult
   */
  LetusResult addContentCategory(long parentId, String name);
  
  /**
   * 根据本节点id 删除商品分类信息
   * 
   * @param id 本节点
   */
  void deleteContentCategory(long id);
  
  /**
   * 更新本节点记录的名称
   * 
   * @param id
   *        本节点id
   * @param name
   *        节点名称
   */
  void updateContentCategory(long id, String name);
  
}
