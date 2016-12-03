/**
 * File：ContentCatgoryServiceImpl.java
 * Package：com.letus.service.impl
 * Author：xfyin
 * Date：2016-12-3 下午4:20:10
 * Copyright (C) 2016-2016 letus179-Copyright
 */
package com.letus.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.letus.common.pojo.EUTreeNode;
import com.letus.common.pojo.LetusResult;
import com.letus.mapper.TbContentCategoryMapper;
import com.letus.pojo.TbContentCategory;
import com.letus.pojo.TbContentCategoryExample;
import com.letus.pojo.TbContentCategoryExample.Criteria;
import com.letus.service.ContentCatgoryService;

/**
 * 后台管理--内容分类CMS系统service实现
 * 
 * @author xfyin
 * 
 */
@Service
public class ContentCatgoryServiceImpl implements ContentCatgoryService {
  
  /**
   * 注入contentCategoryMapper代理
   */
  @Autowired
  private TbContentCategoryMapper contentCategoryMapper;
  
  @Override
  public List<EUTreeNode> queryCatgoryList(long parentId) {
    // 根据parentId查询出节点列表信息
    TbContentCategoryExample example = new TbContentCategoryExample();
    Criteria criteria = example.createCriteria();
    criteria.andParentIdEqualTo(parentId);
    List<TbContentCategory> contentCategories = contentCategoryMapper.selectByExample(example);
    
    // 遍历节点列表，封装EU异步tree
    List<EUTreeNode> treeNodes = new ArrayList<>();
    for (TbContentCategory category : contentCategories) {
      // 创建一个节点
      EUTreeNode node = new EUTreeNode();
      node.setId(category.getId());
      node.setState(category.getIsParent() ? "closed" : "open");
      node.setText(category.getName());
      treeNodes.add(node);
    }
    return treeNodes;
  }
  
  @Override
  public LetusResult addContentCategory(long parentId, String name) {
    // 构造商品分类记录并插入数据库
    TbContentCategory contentCategory = new TbContentCategory();
    contentCategory.setParentId(parentId);
    contentCategory.setName(name);
    contentCategory.setCreated(new Date());
    contentCategory.setUpdated(new Date());
    contentCategory.setStatus(1);
    contentCategory.setIsParent(false);
    contentCategory.setSortOrder(1);
    contentCategoryMapper.insert(contentCategory);
    
    // 查看父节点的isParent列是否为true，若不是就修改为true
    TbContentCategory parentCategory = contentCategoryMapper.selectByPrimaryKey(parentId);
    boolean isParent = parentCategory.getIsParent();
    // 若是false，修改为true，并更新父节点
    if (!isParent) {
      parentCategory.setIsParent(true);
      contentCategoryMapper.updateByPrimaryKey(parentCategory);
    }
    // 需要将新记录返回，页面需要再取id
    return LetusResult.ok(contentCategory);
  }
  
  @Override
  public void deleteContentCategory(long id) {
    
    // 取出本节点的父节点id
    TbContentCategory currentCategory = contentCategoryMapper.selectByPrimaryKey(id);
    long parentId = currentCategory.getParentId();
    
    // 1.若该节点下存在子节点，子节点也需要删除
    TbContentCategoryExample example = new TbContentCategoryExample();
    Criteria criteria = example.createCriteria();
    criteria.andParentIdEqualTo(id);
    List<TbContentCategory> contentCategories = contentCategoryMapper.selectByExample(example);
    if (contentCategories != null && contentCategories.size() != 0) {
      for (TbContentCategory category : contentCategories) {
        contentCategoryMapper.deleteByPrimaryKey(category.getId());
      }
    }
    
    // 3.本节点的父节点下的记录 对应的子节点若只包含本节点，删除本节点后需要将父节点的isParent属性设置为false
    // 父节点对应的记录
    TbContentCategory parentCategory = contentCategoryMapper.selectByPrimaryKey(parentId);
    // 父节点记录的主键id
    Long idParent = parentCategory.getId();
    // 获取父节点下的所有子节点信息
    TbContentCategoryExample exampleChilds = new TbContentCategoryExample();
    Criteria criteriaChilds = exampleChilds.createCriteria();
    criteriaChilds.andParentIdEqualTo(idParent);
    List<TbContentCategory> categoriesChilds = contentCategoryMapper.selectByExample(exampleChilds);
    // 若父节点对应的子节点信心就只有一条，也即是本节点，当本节点被删除后，父节点的isParent设置为false，否则就不做操作
    if (categoriesChilds != null && categoriesChilds.size() == 1) {
      parentCategory.setIsParent(false);
      parentCategory.setUpdated(new Date());
      contentCategoryMapper.updateByPrimaryKey(parentCategory);
    }
    
    // 3.删除本节点记录,必须最后执行这步
    contentCategoryMapper.deleteByPrimaryKey(id);
  }

  @Override
  public void updateContentCategory(long id, String name) {
    TbContentCategory category = contentCategoryMapper.selectByPrimaryKey(id);
    category.setName(name);
    category.setUpdated(new Date());
    contentCategoryMapper.updateByPrimaryKey(category);
  }
  
}
