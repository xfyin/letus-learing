/**
 * File：FTPtest.java
 * Package：com.letus.controller
 * Author：xfyin
 * Date：2016-11-26 下午6:56:45
 * Copyright (C) 2016-2016 letus179-Copyright
 */
package com.letus.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.SocketException;

import org.apache.commons.net.ftp.FTP;
import org.apache.commons.net.ftp.FTPClient;
import org.junit.Test;


/**
 * 
 * 
 * @author xfyin
 *
 */
public class FTPtest {
  
  @Test
  public void testFTPClient() throws SocketException, IOException{
    //创建FTPClient对象
    FTPClient client = new FTPClient();
    //创建FTP连接
    client.connect("192.168.1.118", 21);
    // 登录FTP服务器， 输入用户名密码端口号
    client.login("ftpuser", "4240336");
    //上传文件
    FileInputStream inputStream = new FileInputStream(new File("G:\\zhutou.jpg"));
    // 设置上传的存储路径
    client.changeWorkingDirectory("/home/ftpuser/www/images");
    // 设置上传文件的格式 二进制
    client.setFileType(FTP.BINARY_FILE_TYPE);
    // 第一个参数：服务器端文件名；第二个参数：上传文件的inputStream
    client.storeFile("hello.jpg", inputStream);
    // 关闭连接
    client.logout();
  }
}
