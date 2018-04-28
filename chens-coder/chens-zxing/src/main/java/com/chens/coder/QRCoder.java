package com.chens.coder;

import com.chens.core.util.BaseFileUtil;
import com.chens.core.util.BaseImageUtil;
import com.chens.core.util.UUIDUtil;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.WriterException;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;
import org.springframework.stereotype.Service;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.util.Hashtable;

/**
 * 二维码生成
 *
 * @author songchunlei@qq.com
 * @create 2018/4/28
 */
@Service
public class QRCoder implements IQRCode {

    @Override
    public byte[] create(QRConfig config) throws IOException {

        Hashtable<EncodeHintType, Object> hints = new Hashtable<EncodeHintType, Object>();
        //指定纠错级别--共有四个级别L M Q H， L 7% M 15% Q 25% H 30%
        hints.put(EncodeHintType.MARGIN, 1);
        hints.put(EncodeHintType.ERROR_CORRECTION, ErrorCorrectionLevel.H);
        BitMatrix matrix;
        ByteArrayOutputStream os = null;
        try {
            os = new ByteArrayOutputStream();
            matrix = new MultiFormatWriter().encode(config.getContent(),BarcodeFormat.QR_CODE,
                    config.getWidth(), config.getHeight(),hints);

            if(config.getLogo()!=null)
            {
                //当有Logo文件流
                BufferedImage codeImage = this.overlapImage(matrix,config.getLogo(),config.getFormat(),config.getLogoCent());
                ImageIO.write(codeImage,config.getFormat(),os);
            }
            else
            {
                //没有Logo文件流
                MatrixToImageWriter.writeToStream(matrix, config.getFormat(), os);
            }
        } catch (WriterException e) {
            e.printStackTrace();
        }
        finally {
            if (os!=null)
            {
                os.close();
            }
        }
        return os.toByteArray();
    }

    @Override
    public String create(QRConfig config, String savePath) throws IOException {
        byte[] images = create(config);
        String url = savePath+ File.separator+UUIDUtil.getUUID()+"."+config.getFormat();
        if(images!=null)
        {
            BaseImageUtil.byte2image(images,url);
        }
        return url;
    }

    /**
     * 为二维码增加logo图片
     * @param matrix
     * @param logo
     * @param format
     * @param logoCent
     * @return
     */
    public BufferedImage overlapImage(BitMatrix matrix,byte[] logo, String format, int logoCent) {
        BufferedImage qrCodeImage = null;
        try {
            if(logo != null){
                qrCodeImage = MatrixToImageWriter.toBufferedImage(matrix);
                Graphics2D g = qrCodeImage.createGraphics();
                BufferedImage logoBufferedImage = ImageIO.read(new ByteArrayInputStream(logo));
                int width = qrCodeImage.getWidth() / logoCent;
                int height = qrCodeImage.getHeight() / logoCent;
                int x = (qrCodeImage.getWidth() - width) / 2;
                int y = (qrCodeImage.getHeight() - height) / 2;
                g.drawImage(logoBufferedImage, x, y, width, height, null);
                g.dispose();
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return qrCodeImage;
    }
}
