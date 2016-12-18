/**
 * File：ItemParamItemServiceImpl.java
 * Package：com.letus.service.impl
 * Author：xfyin
 * Date：2016-11-30 下午11:10:42
 * Copyright (C) 2016-2016 letus179-Copyright
 */
package com.letus.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.letus.common.utils.JsonUtils;
import com.letus.mapper.TbItemParamItemMapper;
import com.letus.pojo.TbItemParamItem;
import com.letus.pojo.TbItemParamItemExample;
import com.letus.pojo.TbItemParamItemExample.Criteria;
import com.letus.service.ItemParamItemService;

/**
 * 商品规格参数
 * 
 * @author xfyin
 * 
 */
@Service
public class ItemParamItemServiceImpl implements ItemParamItemService {
  
  /**
   * 注入代理
   */
  @Autowired
  private TbItemParamItemMapper itemParamItemMapper;
  
  @SuppressWarnings({ "rawtypes", "unchecked" })
  @Override
  public String getItemParamItemByItemId(long itemId) {
    TbItemParamItemExample example = new TbItemParamItemExample();
    Criteria criteria = example.createCriteria();
    criteria.andItemIdEqualTo(itemId);
    List<TbItemParamItem> list = itemParamItemMapper.selectByExampleWithBLOBs(example);
    if (list == null || list.size() == 0) {
      return "";
    }
    // 获取规格参数信息
    TbItemParamItem paramItem = list.get(0);
    String paramData = paramItem.getParamData();
    // 调用工具类 将json字符串转换为list
    List<Map> jsonList = JsonUtils.jsonToList(paramData, Map.class);
    // 拼接html
    StringBuffer sb = new StringBuffer();
    sb.append("<table cellpadding=\"0\" cellspacing=\"1\" width=\"100%\" border=\"0\" class=\"Ptable\">\n");
    sb.append("    <tbody>\n");
    for (Map m1 : jsonList) {
      sb.append("        <tr>\n");
      sb.append("            <th class=\"tdTitle\" colspan=\"2\">" + m1.get("group") + "</th>\n");
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
    return sb.toString();
  }
  
}
