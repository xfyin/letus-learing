/**
 * File：PictureServiceImpl.java
 * Package：com.letus.service.impl
 * Author：xfyin
 * Date：2016-11-26 下午11:28:25
 * Copyright (C) 2016-2016 letus179-Copyright
 */
package com.letus.service.impl;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.letus.common.utils.FtpUtil;
import com.letus.common.utils.IDUtil;
import com.letus.service.PictureService;

/**
 * 图片上传/下载服务实现类
 * 
 * @author xfyin
 * 
 */
@Service
public class PictureServiceImpl implements PictureService {
  
  // spring容器中读入ftp.properties文件，通过该注解将对应的属性值取出
  @Value("${FTP_ADDRESS}")
  private String FTP_ADDRESS;
  
  @Value("${FTP_PORT}")
  private Integer FTP_PORT;
  
  @Value("${FTP_USERNAME}")
  private String FTP_USERNAME;
  
  @Value("${FTP_PASSWORD}")
  private String FTP_PASSWORD;
  
  @Value("${FTP_BASE_PATH}")
  private String FTP_BASE_PATH;
  
  @Value("${IMAGE_BASE_URL}")
  private String IMAGE_BASE_URL;
  
  @Override
  public Map<String, Object> uploadPicture(MultipartFile uploadFile) {
    Map<String, Object> map = new HashMap<>();
    // 生成一个新的文件名
    // 原文件名
    String oldName = uploadFile.getOriginalFilename();
    // 新文件名
    String newName = IDUtil.genImageName();
    newName = newName + oldName.substring(oldName.lastIndexOf("."));
    // 文件上传
    String imagePath = new DateTime().toString("/yyyy/MM/dd");
    boolean result = true;
    try {
      result = FtpUtil.uploadFile(FTP_ADDRESS, FTP_PORT, FTP_USERNAME, FTP_PASSWORD, FTP_BASE_PATH, imagePath, newName,
          uploadFile.getInputStream());
    }
    catch (IOException e) {
      result = false;
    }
    // 上传失败
    if (!result) {
      map.put("error", 1);
      map.put("message", "文件上传失败");
      return map;
    }
    // 上传成功
    map.put("error", 0);
    map.put("url", IMAGE_BASE_URL + imagePath + "/" + newName);
    return map;
  }
}
