/**
 * File：PictureController.java
 * Package：com.letus.controller
 * Author：xfyin
 * Date：2016-11-27 上午12:24:49
 * Copyright (C) 2016-2016 letus179-Copyright
 */
package com.letus.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.letus.common.utils.JsonUtils;
import com.letus.service.PictureService;

/**
 * 图片管理
 * 
 * @author xfyin
 * 
 */
@Controller
@RequestMapping("/pic")
public class PictureController {
  
  /**
   * 注入pictureService
   */
  @Autowired
  private PictureService pictureService;
  
  /**
   * 图片上传
   * 
   * @param uploadFile
   *        上传文件信息
   * @return map
   */
  @RequestMapping("/upload")
  @ResponseBody
  public String pictureUpload(MultipartFile uploadFile) {
     Map<String, Object> map = pictureService.uploadPicture(uploadFile);
     return JsonUtils.objectToJson(map);
  }
}
