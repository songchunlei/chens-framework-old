package com.chens.file;

import com.chens.file.entity.SysFile;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * 文件操作服务，保存，读取等
 *
 * @auther songchunlei@qq.com
 * @create 2018/3/9
 */
public interface IFileService {

    /**
     * 根据文件id或md5获取文件流
     * @param idOrMd5
     * @return
     */
    InputStream readFile(String idOrMd5);

    /**
     * 保存文件并返回文件信息
     * @param fileStream
     * @param fileName
     * @param fileType
     * @param creatorId
     * @return
     * @throws IOException
     */
    SysFile saveFile(InputStream fileStream,String fileName,String fileType,String creatorId) throws IOException;

    /**
     * 将已上传的文件写出到指定的输出流
     * @param fileId
     * @param out
     * @param skip
     * @throws IOException
     */
    void writeFile(String fileId, OutputStream out, long skip) throws IOException;

}
