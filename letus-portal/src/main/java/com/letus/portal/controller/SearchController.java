/**
 * File：SearchController.java
 * Package：com.letus.portal.controller
 * Author：xfyin
 * Date：2016-12-11 下午10:49:20
 * Copyright (C) 2016-2016 letus179-Copyright
 */
package com.letus.portal.controller;

import java.io.UnsupportedEncodingException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.letus.common.pojo.SearchResult;
import com.letus.portal.service.SearchService;

/**
 * 商品搜索controller
 * 
 * @author xfyin
 * 
 */
@Controller
public class SearchController {
  
  /**
   * 注入service
   */
  @Autowired
  private SearchService searchService;
  
  @RequestMapping("/search")
  public ModelAndView search(@RequestParam("q") String queryString,
                             @RequestParam(defaultValue = "1") Integer page) {
    ModelAndView mv = new ModelAndView();
    
    // 查询条件为空，返回首页
    if (StringUtils.isEmpty(queryString)) {
      mv.setViewName("index");
      return mv;
    }
    
    // 转码，若失败，返回首页
    try {
      queryString = new String(queryString.getBytes("iso8859-1"), "UTF-8");
    }
    catch (UnsupportedEncodingException e) {
      e.printStackTrace();
      mv.setViewName("index");
      return mv;
    }
    
    SearchResult search = searchService.search(queryString, page);
    // 异常情况下，依然在首页
    if (search == null) {
      mv.setViewName("index");
      return mv;
    }
    // 页面需要的属性值
    mv.addObject("query", queryString);
    mv.addObject("totalPage", search.getPageCount());
    mv.addObject("itemList", search.getItems());
    mv.addObject("page", page);
    // 返回页面search.jsp
    mv.setViewName("search");
    return mv;
    
  }
  
}
