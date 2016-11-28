/**
 * File：PictureService.java
 * Package：com.letus.service
 * Author：xfyin
 * Date：2016-11-26 下午11:22:52
 * Copyright (C) 2016-2016 letus179-Copyright
 */
package com.letus.service;

import java.util.Map;

import org.springframework.web.multipart.MultipartFile;

/**
 * 图片上传下载service
 * 
 * @author xfyin
 * 
 */
public interface PictureService {
  
  /**
   * 图片上传
   * 
   * @param uploadFile
   *        图片对象
   * @return map
   */
  Map<String, Object> uploadPicture(MultipartFile uploadFile);
}
