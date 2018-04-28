package com.chens.coder;

import com.chens.coder.exception.CoderExceptionEnum;
import com.chens.core.constants.CommonConstants;
import com.chens.core.exception.BaseException;
import com.chens.core.exception.FileException;
import com.chens.core.exception.FileExceptionEnum;
import com.chens.core.util.BaseFileUtil;
import com.chens.core.vo.Result;
import com.chens.core.web.BaseController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 二维码控制器
 *
 * @author songchunlei@qq.com
 * @create 2018/4/28
 */
public abstract class BaseQRController extends BaseController{

    @Autowired
    protected IQRCode qrCode;

    /**
     * 二维码生成并返回url地址（保存服务器）
     * @param qrConfig 配置
     * @param savePath 保存地址
     * @return
     */
    @RequestMapping("url")
    public ResponseEntity<Result> url(QRConfig qrConfig,String savePath, HttpServletRequest request, HttpServletResponse response) {
        try {
            return doSuccess(CommonConstants.QUERY_SUCCESS,qrCode.create(qrConfig,savePath));
        } catch (IOException e) {
            throw new FileException(CoderExceptionEnum.QR_CREATE_ERROR.getCode(),e.getMessage());
        }
    }

    /**
     * @Description: 二维码生成并下载（不保存服务器）
     * @param qrConfig 配置
     */
    @RequestMapping("/download")
    public void download(QRConfig qrConfig, HttpServletRequest request, HttpServletResponse response) {
        byte[] image;
        try {
            image = qrCode.create(qrConfig);
        } catch (IOException e) {
            throw new FileException(FileExceptionEnum.FILE_READING_ERROR.getCode(),e.getMessage());
        }
        BaseFileUtil.printResponseOut(request,response,image,"QRCode",qrConfig.getFormat(),qrConfig.getCharCode());
    }


}
