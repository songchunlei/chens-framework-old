package com.chens.file.util;

import org.apache.log4j.Logger;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import javax.imageio.stream.ImageInputStream;
import javax.validation.constraints.NotNull;
import java.io.*;
import java.security.MessageDigest;

/**
 * 文件服务常用工具类
 *
 * @author songchunlei@qq.com
 * @create 2018/3/14
 */
public class FileUtil {

    private static final Logger logger = Logger.getLogger(FileUtil.class);
    private static final int BUFFER_SIZE = 16 * 1024;

    public static boolean isImage(File tempFile)
            throws Exception {
        ImageInputStream is= ImageIO.createImageInputStream(tempFile);
        return is!=null;
    }

    /**
     * 取得文件扩展名
     * @param fileName 文件全名
     * @return 文件扩展名(例如: .gif)
     */
    public static String getExtention(String fileName) {
        int pos = fileName.lastIndexOf(".");
        return fileName.substring(pos);
    }

    public static StringBuilder createMd5(final MultipartFile file)
            throws Exception {
        StringBuilder sb = new StringBuilder();
        //生成MD5实例
        MessageDigest md5 = MessageDigest.getInstance("MD5");
        InputStream inputStream = file.getInputStream();
        int available = inputStream.available();
        byte[] bytes = new byte[available];
        md5.update(bytes);
        for (byte by : md5.digest()) {
            //将生成的字节MD5值转换成字符串
            sb.append(String.format("%02X", by));
        }
        return sb;
    }

    /**
     * 文件拷贝
     * @param src
     * @param dst
     */
    public static void copy(File src, File dst) {
        mkDirs(dst.getPath());
        try (InputStream in = new FileInputStream(src);
             OutputStream out = new FileOutputStream(dst);) {
            copy(in, out);
        } catch (Exception e) {
            logger.error(e);
        }
    }

    /**
     * 文件流拷贝
     * @param in
     * @param out
     */
    public static void copy(InputStream in, OutputStream out) {
        try (BufferedInputStream bufferedInputStream = new BufferedInputStream(in, BUFFER_SIZE);
             BufferedOutputStream bufferedOutputStream =
                     new BufferedOutputStream(out, BUFFER_SIZE)) {
            byte[] buffer = new byte[BUFFER_SIZE];
            while (bufferedInputStream.read(buffer) > 0) {
                bufferedOutputStream.write(buffer);
            }
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        }
    }

    /**
     * 创建文件
     * @param path
     * @return
     * @throws IOException
     */
    public static File createFile(String path) throws IOException {
        File file;
        file = new File(path);
        if (!file.exists()) {
            file.createNewFile();
        }
        return file;
    }

    /**
     * 创建目录
     * @param path
     */
    public static void mkDirs(String path) {
        File file = new File(path);
        if (!file.getParentFile().exists()) {
            file.getParentFile().mkdirs();
        }
    }



    /**
     * 删除指定文件夹
     * @param folderPath 文件夹路径
     * @return 是否删除成功
     */
    @SuppressWarnings("ResultOfMethodCallIgnored")
    public static boolean deleteFolder(@NotNull final String folderPath) {
        File dir = new File(folderPath);
        File[] files = dir.listFiles();
        if(files!=null){
            for (File file : files) {
                try {
                    file.delete();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
        return dir.delete();
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
            throw e;
        } finally {
            outputStream.close();
            inputStream.close();
        }
    }
}
