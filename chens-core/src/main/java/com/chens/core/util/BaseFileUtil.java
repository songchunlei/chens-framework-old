package com.chens.core.util;

import com.chens.core.constants.CommonConstants;
import com.chens.core.exception.FileException;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.constraints.NotNull;
import java.io.*;
import java.net.URLEncoder;

/**
 * 文件操作
 *
 * @author songchunlei@qq.com
 * @create 2018/4/28
 */
public class BaseFileUtil {

    private static final Logger logger = Logger.getLogger(BaseFileUtil.class);
    private static final int BUFFER_SIZE = 16 * 1024;

    /**
     * 取得文件扩展名
     * @param fileName 文件全名
     * @return 文件扩展名(例如: .gif)
     */
    public static String getExtention(String fileName) {
        int pos = fileName.lastIndexOf(".");
        return fileName.substring(pos);
    }

    /**
     * 文件-->byte[]
     * @param filePath
     * @return
     */
    public static byte[] File2byte(String filePath)
    {
        byte[] buffer = null;
        try
        {
            File file = new File(filePath);
            FileInputStream fis = new FileInputStream(file);
            ByteArrayOutputStream bos = new ByteArrayOutputStream();
            byte[] b = new byte[1024];
            int n;
            while ((n = fis.read(b)) != -1)
            {
                bos.write(b, 0, n);
            }
            fis.close();
            bos.close();
            buffer = bos.toByteArray();
        }
        catch (FileNotFoundException e)
        {
            e.printStackTrace();
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
        return buffer;
    }

    /**
     * byte[]-->文件
     * @param buf
     * @param filePath
     * @param fileName
     */
    public static void byte2File(byte[] buf, String filePath, String fileName)
    {
        BufferedOutputStream bos = null;
        FileOutputStream fos = null;
        File file = null;
        try
        {
            File dir = new File(filePath);
            if (!dir.exists() && dir.isDirectory())
            {
                dir.mkdirs();
            }
            file = new File(filePath + File.separator + fileName);
            fos = new FileOutputStream(file);
            bos = new BufferedOutputStream(fos);
            bos.write(buf);
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        finally
        {
            if (bos != null)
            {
                try
                {
                    bos.close();
                }
                catch (IOException e)
                {
                    e.printStackTrace();
                }
            }
            if (fos != null)
            {
                try
                {
                    fos.close();
                }
                catch (IOException e)
                {
                    e.printStackTrace();
                }
            }
        }
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
     * 打印输出文件
     * @param request
     * @param response
     * @param fileBytes
     * @param fileName
     * @param ext
     * @param characterEncoding
     */
    public static void printResponseOut(HttpServletRequest request, HttpServletResponse response,byte[] fileBytes, String fileName, String ext, String characterEncoding)
    {
        try (InputStream in = new ByteArrayInputStream(fileBytes); OutputStream out = response.getOutputStream();) {
            response.setContentType(ext);
            /*"UTF-8"*/
            response.setCharacterEncoding(characterEncoding);
            // 解决不同浏览器中文文件名乱码问题
            if (request.getHeader("User-Agent").toLowerCase().indexOf("firefox") > 0) {
                // firefox浏览器
                response.setHeader("Content-disposition", "attachment; filename=" + new String(fileName.getBytes(CommonConstants.CHARACTER_UTF8), CommonConstants.CHARACTER_ISO88591));
            } else {
                response.setHeader("Content-disposition", "attachment; filename=" + URLEncoder.encode(fileName, CommonConstants.CHARACTER_UTF8));
            }
            copy(in, out);
            out.flush();
        } catch (IOException e) {
            logger.error(e.getMessage(), e);
        } catch (FileException e) {
            logger.error(e.getMessage(), e);
        }
    }



}
