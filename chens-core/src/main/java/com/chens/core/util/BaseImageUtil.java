package com.chens.core.util;

import org.apache.log4j.Logger;

import javax.imageio.ImageIO;
import javax.imageio.stream.FileImageInputStream;
import javax.imageio.stream.FileImageOutputStream;
import javax.imageio.stream.ImageInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

/**
 * 图片操作
 *
 * @author songchunlei@qq.com
 * @create 2018/4/28
 */
public class BaseImageUtil {

    private static final Logger logger = Logger.getLogger(BaseImageUtil.class);

    /**
     * 判断是否为图片
     * @param tempFile
     * @return
     * @throws Exception
     */
    public static boolean isImage(File tempFile)
            throws Exception {
        ImageInputStream is= ImageIO.createImageInputStream(tempFile);
        return is!=null;
    }

    /**
     * 图片到byte数组
     * @param path
     * @return
     */
    public static byte[] image2byte(String path){
        byte[] data = null;
        FileImageInputStream input = null;
        try {
            input = new FileImageInputStream(new File(path));
            ByteArrayOutputStream output = new ByteArrayOutputStream();
            byte[] buf = new byte[1024];
            int numBytesRead = 0;
            while ((numBytesRead = input.read(buf)) != -1) {
                output.write(buf, 0, numBytesRead);
            }
            data = output.toByteArray();
            output.close();
            input.close();
        }
        catch (FileNotFoundException ex) {
            logger.error(ex.getMessage());
        }
        catch (IOException ex) {
            logger.error(ex.getMessage());
        }
        return data;
    }

    /**
     * byte数组到图片
     * @param data
     * @param path
     */
    public static void byte2image(byte[] data,String path){
        if(data.length<3||path.equals("")) {
            return;
        }
        try{
            FileImageOutputStream imageOutput = new FileImageOutputStream(new File(path));
            imageOutput.write(data, 0, data.length);
            imageOutput.close();
            logger.debug("Make Picture success,Please find image in " + path);
        } catch(Exception ex) {
            logger.error(ex.getMessage());
        }
    }

    /**
     * byte数组到16进制字符串
     * @param data
     * @return
     */
    public static String byte2string(byte[] data){
        if(data==null||data.length<=1)
        {
            return "0x";
        }
        if(data.length>200000)
        {
            return "0x";
        }
        StringBuffer sb = new StringBuffer();
        int buf[] = new int[data.length];
        //byte数组转化成十进制
        for(int k=0;k<data.length;k++){
            buf[k] = data[k]<0?(data[k]+256):(data[k]);
        }
        //十进制转化成十六进制
        for(int k=0;k<buf.length;k++){
            if(buf[k]<16)
            {
                sb.append("0"+Integer.toHexString(buf[k]));
            }
            else
            {
                sb.append(Integer.toHexString(buf[k]));
            }
        }
        return "0x"+sb.toString().toUpperCase();
    }
}
