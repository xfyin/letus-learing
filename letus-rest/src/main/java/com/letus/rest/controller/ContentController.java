/**
 * File：ContentController.java
 * Package：com.letus.rest.controller
 * Author：xfyin
 * Date：2016-12-4 下午8:58:48
 * Copyright (C) 2016-2016 letus179-Copyright
 */
package com.letus.rest.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.letus.common.pojo.LetusResult;
import com.letus.common.utils.ExceptionUtil;
import com.letus.pojo.TbContent;
import com.letus.rest.service.ContentService;

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
  
  @RequestMapping("/list/{categoryId}")
  @ResponseBody
  public LetusResult queryContentList(@PathVariable long categoryId) {
    try {
      List<TbContent> contents = contentService.queryContentList(categoryId);
      return LetusResult.ok(contents);
    }
    catch (Exception e) {
      e.printStackTrace();
      return LetusResult.build(102, ExceptionUtil.getStackTrace(e));
    }
  }
}
