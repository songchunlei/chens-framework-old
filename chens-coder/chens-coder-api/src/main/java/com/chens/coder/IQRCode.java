package com.chens.coder;

import java.io.IOException;

/**
 * 二维码生成器接口
 *
 * @author songchunlei@qq.com
 * @create 2018/4/28
 */
public interface IQRCode {

    /**
     * 创建
     * @param config
     * @throws IOException
     * @return byte[] 返回二进制流
     */
    byte[] create(QRConfig config) throws IOException;

    /**
     * 创建并保存
     * @param config 配置文件
     * @param savePath 保存位置
     * @throws IOException
     * @return String 返回二URL地址
     */
    String create(QRConfig config,String savePath) throws IOException;



}
