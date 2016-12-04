/**
 * File：ContentController.java
 * Package：com.letus.controller
 * Author：xfyin
 * Date：2016-12-4 下午7:19:44
 * Copyright (C) 2016-2016 letus179-Copyright
 */
package com.letus.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.letus.common.pojo.EUDataGridResult;
import com.letus.common.pojo.LetusResult;
import com.letus.pojo.TbContent;
import com.letus.service.ContentService;

/**
 * 
 * 
 * @author xfyin
 * 
 */
@Controller
@RequestMapping("/content")
public class ContentController {
  
  /**
   * 注入service
   */
  @Autowired
  private ContentService contentService;
  
  /**
   * 分页显示内容管理信息
   * 
   * @param page
   *        第几页
   * @param rows
   *        每页显示多少记录
   * @param categoryId
   *        分类id
   * @return EUDataGridResult
   */
  @RequestMapping("/query/list")
  @ResponseBody
  public EUDataGridResult queryContentList(int page, int rows, long categoryId) {
    return contentService.queryContentList(page, rows, categoryId);
  }
  
  /**
   * 新增 商品内容（pojo接收表单信息，需要pojo属性和表单的name 一致）
   * 
   * @param content
   *        商品内容
   * @return LetusResult
   */
  @RequestMapping("/save")
  @ResponseBody
  public LetusResult addContent(TbContent content) {
    return contentService.addContent(content);
  }
  
  /**
   * 修改 商品内容
   * 
   * @param content
   *        商品内容
   * @return LetusResult
   */
  @RequestMapping("/edit")
  @ResponseBody
  public LetusResult modifyContent(TbContent content) {
    return contentService.modifyContent(content);
  }
  
  /**
   * 批量删除商品内容信息
   * @param ids 多条商品内容的id
   * @return
   */
  @RequestMapping("/delete")
  @ResponseBody
  public LetusResult deleteContent(String ids) {
    String[] iD = ids.split(",");
    for (String id : iD) {
      contentService.deleteContent(Long.valueOf(id));
    }
    return LetusResult.ok();
  }
  
}
