package com.chens.file.service;

import com.chens.file.vo.FileData;
import com.chens.file.vo.FileInfo;

/**
 * 文件操作服务，保存，读取等
 *
 * @auther songchunlei@qq.com
 * @create 2018/3/9
 */
public interface IFileService {


    FileInfo saveByFileInfo(FileData fileData);

}
