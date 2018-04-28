package com.chens.coder.test;

import com.chens.coder.QRConfig;
import com.chens.coder.IQRCode;
import com.chens.coder.QRCoder;
import com.chens.core.util.BaseImageUtil;
import org.junit.Test;

import javax.imageio.stream.FileImageOutputStream;
import java.io.*;

/**
 * 测试
 *
 * @author songchunlei@qq.com
 * @create 2018/4/28
 */
public class TestMain {

    @Test
    public void testQRCoder() throws IOException {
        QRConfig config = new QRConfig("http://127.0.0.1/id",200,200,"png","utf-8");
        IQRCode qrCode = new QRCoder();
        byte[] bytes = qrCode.create(config);
        byte2image(bytes,"/opt/upload/qr.png");
    }

    @Test
    public void testLogoQRCoder() throws IOException {


        byte[] logoBytes = BaseImageUtil.image2byte("/opt/upload/7515549D235B4466AAE917E3CD40CD13.png");

        QRConfig config = new QRConfig("http://127.0.0.1/id",200,200,"png","utf-8",logoBytes,5);
        IQRCode qrCode = new QRCoder();
        byte[] bytes = qrCode.create(config);
        byte2image(bytes,"/opt/upload/qr2.png");
    }

    public void byte2image(byte[] data,String path){
        if(data.length<3||path.equals("")) return;//判断输入的byte是否为空
        try{
            FileImageOutputStream imageOutput = new FileImageOutputStream(new File(path));//打开输入流
            imageOutput.write(data, 0, data.length);//将byte写入硬盘
            imageOutput.close();
            System.out.println("Make Picture success,Please find image in " + path);
        } catch(Exception ex) {
            System.out.println("Exception: " + ex);
            ex.printStackTrace();
        }
    }


}
