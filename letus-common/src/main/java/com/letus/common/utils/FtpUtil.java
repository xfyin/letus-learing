/**
 * File：FtpUtil.java
 * Package：com.letus.common.utils
 * Author：xfyin
 * Date：2016-11-26 下午8:00:03
 * Copyright (C) 2016-2016 letus179-Copyright
 */
package com.letus.common.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.SocketException;

import org.apache.commons.net.ftp.FTP;
import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPFile;
import org.apache.commons.net.ftp.FTPReply;

/**
 * ftp上传、下载工具类
 * 
 * @author xfyin
 * 
 */
public class FtpUtil {
  
  /**
   * 向ftp服务器上传文件
   * 
   * @param host
   *        FTP服务器hostname
   * @param port
   *        FTP服务器端口
   * @param username
   *        FTP服务器登录账号
   * @param password
   *        FTP服务器登录密码
   * @param basePath
   *        FTP服务器基础目录
   * @param filePath
   *        FTP服务器文件存放路径，例如分日期存放:/2016/11/26,文件的路径为：basePath+filePath
   * @param filename
   *        上传到FTP服务器上的文件名
   * @param input
   *        输入流
   * @return 上传成功true，失败false
   */
  public static boolean uploadFile(String host, int port, String username, String password, String basePath,
                                   String filePath, String filename, InputStream input) {
    FTPClient ftp = new FTPClient();
    try {
      // 连接FTP服务器，若采用默认端口21，可以直接用ftp.connect(host);
      ftp.connect(host, port);
      // 登录
      ftp.login(username, password);
      int replyCode = ftp.getReplyCode();
      if (!FTPReply.isPositiveCompletion(replyCode)) {
        ftp.disconnect();
        return false;
      }
      // 切换到上传目录
      if (!ftp.changeWorkingDirectory(basePath + filePath)) {
        // 如果目录不存在创建目录
        String[] dirs = filePath.split("/");
        String tempPath = basePath;
        for (String dir : dirs) {
          if (null == dir || "".equals(dir)) {
            continue;
          }
          tempPath += "/" + dir;
          if (!ftp.changeWorkingDirectory(tempPath)) {
            if (!ftp.makeDirectory(tempPath)) {
              return false;
            }
            else {
              ftp.changeWorkingDirectory(tempPath);
            }
          }
        }
      }
      // 设置上传文件的类型为二进制类型
      ftp.setFileType(FTP.BINARY_FILE_TYPE);
      // 上传文件
      if (!ftp.storeFile(filename, input)) {
        return false;
      }
      input.close();
      ftp.logout();
    }
    catch (SocketException e) {
      e.printStackTrace();
    }
    catch (IOException e) {
      e.printStackTrace();
    }
    finally {
      if (ftp.isConnected()) {
        try {
          ftp.disconnect();
        }
        catch (IOException e) {
          e.printStackTrace();
        }
      }
    }
    return true;
  }
  
  /**
   * 从FTP服务器下载文件
   * 
   * @param host
   *        FTP服务器hostname
   * @param port
   *        FTP服务器端口
   * @param username
   *        FTP登录账号
   * @param password
   *        FTP登录密码
   * @param remotePath
   *        FTP服务器上的相对路径
   * @param fileName
   *        要下载的文件名
   * @param localPath
   *        载后保存到本地的路径
   * @return 下载成功true，失败false
   */
  public static boolean downloadFile(String host, int port, String username, String password, String remotePath,
                                     String fileName, String localPath) {
    FTPClient ftp = new FTPClient();
    try {
      ftp.connect(host, port);
      // 如果采用默认端口，可以使用ftp.connect(host)的方式直接连接FTP服务器
      ftp.login(username, password);// 登录
      int reply = ftp.getReplyCode();
      if (!FTPReply.isPositiveCompletion(reply)) {
        ftp.disconnect();
        return false;
      }
      ftp.changeWorkingDirectory(remotePath);// 转移到FTP服务器目录
      FTPFile[] fs = ftp.listFiles();
      for (FTPFile ff : fs) {
        if (ff.getName().equals(fileName)) {
          File localFile = new File(localPath + "/" + ff.getName());
          OutputStream is = new FileOutputStream(localFile);
          ftp.retrieveFile(ff.getName(), is);
          is.close();
        }
      }
      ftp.logout();
    }
    catch (IOException e) {
      e.printStackTrace();
    }
    finally {
      if (ftp.isConnected()) {
        try {
          ftp.disconnect();
        }
        catch (IOException e) {
        }
      }
    }
    return true;
  }
  
  public static void main(String[] args) {
    try {
      FileInputStream in = new FileInputStream(new File("G:\\aa\\zhu.jpg"));
      boolean flag = uploadFile("192.168.1.118", 21, "ftpuser", "4240336", "/home/ftpuser/www/images", "/2016/11/26",
          "zh.jpg", in);
      System.out.println(flag);
    }
    catch (FileNotFoundException e) {
      e.printStackTrace();
    }
    
  }
}
