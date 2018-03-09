package com.chens.file.service.impl;

import com.chens.file.IFileService;
import com.chens.file.entity.SysFile;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * FastDFS实现
 *
 * @auther songchunlei@qq.com
 * @create 2018/3/9
 */
public class FastDFSServiceImpl implements IFileService{
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
