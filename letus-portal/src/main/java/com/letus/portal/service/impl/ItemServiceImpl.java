/**
 * File：ItemServiceImpl.java
 * Package：com.letus.portal.service.impl
 * Author：xfyin
 * Date：2016-12-13 下午8:17:05
 * Copyright (C) 2016-2016 letus179-Copyright
 */
package com.letus.portal.service.impl;

import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.letus.common.pojo.LetusResult;
import com.letus.common.utils.HttpClientUtil;
import com.letus.common.utils.JsonUtils;
import com.letus.pojo.TbItemDesc;
import com.letus.pojo.TbItemParamItem;
import com.letus.portal.pojo.ItemInfo;
import com.letus.portal.service.ItemService;

/**
 * 商品详情service
 * 
 * @author xfyin
 * 
 */
@Service
public class ItemServiceImpl implements ItemService {
  
  /**
   * 基础URL
   */
  @Value("${REST_BASE_URL}")
  private String REST_BASE_URL;
  
  /**
   * 商品基本信息url
   */
  @Value("${ITEM_BASE_INFO_URL}")
  private String ITEM_BASE_INFO_URL;
  
  /**
   * 商品描述信息URL
   */
  @Value("${ITEM_DESC_INFO_URL}")
  private String ITEM_DESC_INFO_URL;
  
  /**
   * 商品规格参数信息URL
   */
  @Value("${ITEM_PARAM_ITEM_INFO_URL}")
  private String ITEM_PARAM_ITEM_INFO_URL;
  
  @Override
  public ItemInfo queryItemBaseInfoById(long itemId) {
    
    try {
      String itemJson = HttpClientUtil.doGet(REST_BASE_URL + ITEM_BASE_INFO_URL + itemId);
      if (!StringUtils.isBlank(itemJson)) {
        LetusResult letusResult = LetusResult.formatToPojo(itemJson, ItemInfo.class);
        if (letusResult.getStatus() == 200) {
          return (ItemInfo) letusResult.getData();
        }
      }
    }
    catch (Exception e) {
      e.printStackTrace();
    }
    return null;
  }
  
  @Override
  public String queryItemDescInfo(long itemId) {
    try {
      String itemJson = HttpClientUtil.doGet(REST_BASE_URL + ITEM_DESC_INFO_URL + itemId);
      if (!StringUtils.isBlank(itemJson)) {
        LetusResult letusResult = LetusResult.formatToPojo(itemJson, TbItemDesc.class);
        if (letusResult.getStatus() == 200) {
          // 商品描述信息
          return ((TbItemDesc) letusResult.getData()).getItemDesc();
        }
      }
    }
    catch (Exception e) {
      e.printStackTrace();
    }
    return null;
  }
  
  @SuppressWarnings({ "unchecked", "rawtypes" })
  @Override
  public String queryItemParamItem(long itemId) {
    try {
      String itemJson = HttpClientUtil.doGet(REST_BASE_URL + ITEM_PARAM_ITEM_INFO_URL + itemId);
      if (!StringUtils.isBlank(itemJson)) {
        LetusResult letusResult = LetusResult.formatToPojo(itemJson, TbItemParamItem.class);
        if (letusResult.getStatus() != 200) {
          return null;
        }
        // 商品规格参数信息
        String paramItemJson = ((TbItemParamItem) letusResult.getData()).getParamData();
        // 调用工具类 将json字符串转换为list
        List<Map> jsonList = JsonUtils.jsonToList(paramItemJson, Map.class);
        // 拼接html
        StringBuffer sb = new StringBuffer();
        sb.append("<table cellpadding=\"0\" cellspacing=\"1\" width=\"100%\" border=\"0\" class=\"Ptable\">\n");
        sb.append("    <tbody>\n");
        for (Map m1 : jsonList) {
          sb.append("        <tr>\n");
          sb.append("            <th class=\"tdTitle\" colspan=\"2\">" + m1.get("group")
              + "</th>\n");
          sb.append("        </tr>\n");
          List<Map> list2 = (List<Map>) m1.get("params");
          for (Map m2 : list2) {
            sb.append("        <tr>\n");
            sb.append("            <td class=\"tdTitle\">" + m2.get("k") + "</td>\n");
            sb.append("            <td>" + m2.get("v") + "</td>\n");
            sb.append("        </tr>\n");
          }
        }
        sb.append("    </tbody>\n");
        sb.append("</table>");
        // 返回html字符串片段
        return sb.toString();
      }
    }
    catch (Exception e) {
      e.printStackTrace();
    }
    return null;
  }
  
}
