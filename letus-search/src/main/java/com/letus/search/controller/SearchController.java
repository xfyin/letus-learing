/**
 * File：SearchController.java
 * Package：com.letus.search.controller
 * Author：xfyin
 * Date：2016-12-11 下午12:53:39
 * Copyright (C) 2016-2016 letus179-Copyright
 */
package com.letus.search.controller;

import org.apache.solr.client.solrj.SolrServerException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.letus.common.pojo.LetusResult;
import com.letus.common.utils.ExceptionUtil;
import com.letus.search.pojo.SearchResult;
import com.letus.search.service.SearchService;

/**
 * 商品查询 索引controller
 * 
 * @author xfyin
 * 
 */
@Controller
@RequestMapping("/search")
public class SearchController {
  
  /**
   * 注入service
   */
  @Autowired
  private SearchService searchService;
  
  /**
   * 查询商品列表
   * 
   * @param queryString
   *        查询条件，默认的传递参数为 "q"
   * @param page
   *        第几页（默认为第1页）
   * @param rows
   *        每页显示几天记录 （默认显示60条记录）
   * @return LetusResult封装了需要查询到的商品列表
   */
  @RequestMapping(value = "/query", method = RequestMethod.GET)
  @ResponseBody
  public LetusResult search(@RequestParam("q") String queryString,
                            @RequestParam(defaultValue = "1") Integer page,
                            @RequestParam(defaultValue = "60") Integer rows) {
    // 查询条件校验
    if (StringUtils.isEmpty(queryString)) {
      return LetusResult.build(102, "查询条件不能为空");
    }
    
    try {
      // 解决乱码
      queryString = new String(queryString.getBytes("iso8859-1"), "UTF-8");
      SearchResult searchResult = searchService.search(queryString, page, rows);
      return LetusResult.ok(searchResult);
    }
    catch (Exception e) {
      e.printStackTrace();
      return LetusResult.build(102, ExceptionUtil.getStackTrace(e));
    }
  }
}
