package com.chens.file.service;

import com.chens.file.entity.SysFile;
import com.chens.file.vo.FileData;

/**
 * 文件操作服务，保存，读取等
 *
 * @auther songchunlei@qq.com
 * @create 2018/3/9
 */
public interface IFileService {

    /**
     * 上传
     * @param fileData
     * @return
     */
    SysFile upload(FileData fileData);

    /**
     * 下载
     * @param name
     * @return
     */
    FileData download(String name);

    /**
     * 删除文件
     * @param name
     * @return
     */
    boolean deleteByName(String name);



}
