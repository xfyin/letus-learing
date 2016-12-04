/**
 * File：ContentService.java
 * Package：com.letus.service
 * Author：xfyin
 * Date：2016-12-4 下午7:10:40
 * Copyright (C) 2016-2016 letus179-Copyright
 */
package com.letus.service;

import com.letus.common.pojo.EUDataGridResult;
import com.letus.common.pojo.LetusResult;
import com.letus.pojo.TbContent;

/**
 * 内容管理
 * 
 * @author xfyin
 * 
 */
public interface ContentService {
  
  /**
   * 查询内容管理列表
   * 
   * @param page
   *        第几页
   * @param rows
   *        每页显示多少条记录
   * @param categoryId
   *        商品分类id
   * @return EUDataGridResult
   */
  EUDataGridResult queryContentList(int page, int rows, long categoryId);
  
  /**
   * 新增内容
   * 
   * @param content
   *        内容信息
   * @return LetusResult
   */
  LetusResult addContent(TbContent content);
  
  /**
   * 修改商品内容
   * 
   * @param content
   *        商品内容
   * @return LetusResult
   */
  LetusResult modifyContent(TbContent content);
  
  /**
   * 根据主键查询商品内容
   * 
   * @param id
   *        主键
   * @return 商品内容
   */
  TbContent queryContent(long id);
  
  /**
   * 根据id 删除商品内容
   * 
   * @param id
   *        主键
   */
  void deleteContent(long id);
  
}
