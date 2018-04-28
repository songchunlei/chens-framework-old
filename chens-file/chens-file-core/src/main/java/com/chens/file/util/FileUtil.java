package com.chens.file.util;


import com.chens.core.util.BaseFileUtil;
import com.chens.core.util.StringUtils;
import com.chens.core.util.UUIDUtil;
import com.chens.core.exception.FileException;
import com.chens.core.exception.FileExceptionEnum;
import com.chens.file.exception.FolderExceptionEnum;
import com.chens.file.vo.FileData;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.NotNull;
import java.io.*;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * 文件服务工具类
 *
 * @author songchunlei@qq.com
 * @create 2018/3/14
 */
public class FileUtil extends BaseFileUtil{

    /**
     * 根据文件创建md5
     * @param file
     * @return
     * @throws Exception
     */
    public static StringBuilder createMd5(final MultipartFile file) {
        StringBuilder sb = new StringBuilder();
        //生成MD5实例
        InputStream inputStream = null;
        MessageDigest md5 = null;
        int available = 0;
        try {
            md5 = MessageDigest.getInstance("MD5");
            inputStream = file.getInputStream();
            inputStream.available();
        } catch (IOException e) {
            throw new FileException(FileExceptionEnum.FILE_READING_ERROR.getCode(),e.getMessage());
        }
        catch (NoSuchAlgorithmException e) {
            throw new FileException(FileExceptionEnum.FILE_MD5_ERROR.getCode(),e.getMessage());
        }
        byte[] bytes = new byte[available];
        md5.update(bytes);
        for (byte by : md5.digest()) {
            //将生成的字节MD5值转换成字符串
            sb.append(String.format("%02X", by));
        }
        return sb;
    }

    /**
     * 根据文件创建文件数据
     * @param file
     * @return
     * @throws Exception
     */
    public static FileData getFileData(final MultipartFile file,String groupId)  {
        String orgName = file.getOriginalFilename();
        String UUIDStr = UUIDUtil.getUUID();
        //新建文件模拟groupId
        if (StringUtils.isEmpty(groupId))
        {
            groupId = UUIDStr;
        }
        try {
            return new FileData(groupId,UUIDStr, orgName, getExtention(file.getOriginalFilename()), file.getSize(), createMd5(file).toString(),file.getBytes());
        } catch (IOException e) {
            throw new FileException(FileExceptionEnum.FILE_READING_ERROR.getCode(),e.getMessage());
        }
    }

    /**
     * 合并文件
     * @param chunksNumber
     * @param ext
     * @param guid
     * @param uploadFolderPath
     * @throws Exception
     */
    public static void mergeFile(final int chunksNumber,
                                 @NotNull final String ext,
                                 @NotNull final String guid,
                                 @NotNull final String uploadFolderPath)
            throws Exception {
        /*合并输入流*/
        String mergePath = uploadFolderPath + guid + "/";
        SequenceInputStream s ;
        InputStream s1 = new FileInputStream(mergePath + 0 + ext);
        InputStream s2 = new FileInputStream(mergePath + 1 + ext);
        s = new SequenceInputStream(s1, s2);
        for (int i = 2; i < chunksNumber; i++) {
            InputStream s3 = new FileInputStream(mergePath + i + ext);
            s = new SequenceInputStream(s, s3);
        }

        //通过输出流向文件写入数据
        saveStreamToFile(s, uploadFolderPath + guid + ext);

        //删除保存分块文件的文件夹
        deleteFolder(mergePath);

    }

    /**
     * 从stream中保存文件
     *
     * @param inputStream inputStream
     * @param filePath    保存路径
     * @throws Exception 异常 抛异常代表失败了
     */
    public static void saveStreamToFile(final InputStream inputStream,
                                        final String filePath)
            throws Exception {
         /*创建输出流，写入数据，合并分块*/
        OutputStream outputStream = new FileOutputStream(filePath);
        byte[] buffer = new byte[1024];
        int len = 0;
        try {
            while ((len = inputStream.read(buffer)) != -1) {
                outputStream.write(buffer, 0, len);
                outputStream.flush();
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw new FileException(FileExceptionEnum.FILE_SAVE_ERROR.getCode(),e.getMessage());
        } finally {
            outputStream.close();
            inputStream.close();
        }
    }

}
