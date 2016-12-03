/**
 * File：ContentCatgoryController.java
 * Package：com.letus.controller
 * Author：xfyin
 * Date：2016-12-3 下午4:32:53
 * Copyright (C) 2016-2016 letus179-Copyright
 */
package com.letus.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.letus.common.pojo.EUTreeNode;
import com.letus.common.pojo.LetusResult;
import com.letus.service.ContentCatgoryService;

/**
 * 后台管理--内容分类CMS系统
 * 
 * @author xfyin
 * 
 */
@Controller
@RequestMapping("/content/category")
public class ContentCatgoryController {
  
  /**
   * 注入service
   */
  @Autowired
  private ContentCatgoryService contentCatgoryService;
  
  /**
   * 获取商品分类节点列表
   * 
   * @param parentId
   *        后台传过来的id，也即分类表中的parentId 默认值0
   * @return 异步Tree格式的集合
   */
  @RequestMapping("/list")
  @ResponseBody
  public List<EUTreeNode> queryCatgoryList(@RequestParam(value = "id", defaultValue = "0") long parentId) {
    return contentCatgoryService.queryCatgoryList(parentId);
  }
  
  /**
   * 创建新的商品分类节点
   * 
   * @param parentId
   *        该记录的父节点id
   * @param name
   *        该节点的名称
   * @return 封装了该记录的LetusResult
   */
  @RequestMapping("/create")
  @ResponseBody
  public LetusResult addContentCategory(long parentId, String name) {
    return contentCatgoryService.addContentCategory(parentId, name);
  }
  
  /**
   * 删除商品分类节点
   * 
   * @param id
   *        该记录的主键id
   */
  @RequestMapping("/delete")
  @ResponseBody
  public void deleteContentCategory(long id) {
    contentCatgoryService.deleteContentCategory(id);
  }
  
  /**
   * 更新节点信息（重命名）
   * 
   * @param id
   *        节点id
   * @param name
   *        节点名称
   */
  @RequestMapping("/update")
  public void updateContentCategory(long id, String name) {
    contentCatgoryService.updateContentCategory(id, name);
  }
  
}
