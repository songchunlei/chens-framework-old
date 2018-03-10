package com.chens.file.service.impl;

import com.chens.file.IFileService;
import com.chens.file.entity.SysFile;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * 基本文件服务
 *
 * @auther songchunlei@qq.com
 * @create 2018/3/9
 */
public class SimpleFileServiceImpl implements IFileService {
    @Override
    public InputStream readFile(String idOrMd5) {
        return null;
    }

    @Override
    public SysFile saveFile(InputStream fileStream, String fileName, String fileType, String creatorId) throws IOException {
        return null;
    }

    @Override
    public void writeFile(String fileId, OutputStream out, long skip) throws IOException {

    }
}
