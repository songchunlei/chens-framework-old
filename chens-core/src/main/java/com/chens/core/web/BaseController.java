package com.chens.core.web;

import java.io.UnsupportedEncodingException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ch.qos.logback.core.util.FileUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;

import com.baomidou.mybatisplus.plugins.Page;
import com.chens.core.enums.IBaseEnum;
import com.chens.core.util.AopTargetUtil;
import com.chens.core.util.ResultHelper;
import com.chens.core.vo.QueryPageEntity;
import com.chens.core.vo.Result;

/**
 * 基础web包
 *
 * @author songchunlei@qq.com
 * @create 2018/3/5
 */
public class BaseController {

    private Class<?> clazz;
    protected Logger logger;

//    private final String EMPTY_MSG = "";


    protected BaseController() {
        clazz = AopTargetUtil.getSuperClassGenricType(getClass(), 0);
        logger = LoggerFactory.getLogger(clazz);
    }

    /**
     * redirect跳转
     */
    public String redirect(String url) {
        return "redirect:" + url;
    }

    /**
     * forward跳转 转发
     */
    public String forward(String url) {
        return "forward:" + url;
    }


    /**
     * 成功反馈通用方法
     * @param msg
     * @param obj
     * @return
     */
    public ResponseEntity<Result> doSuccess(String msg ,Object obj) {
        return ResponseEntity.ok(ResultHelper.getSuccess(msg,obj));
    }

    /**
     * 成功反馈
     * @param msg
     * @return
     */
    public ResponseEntity<Result> doSuccess(String msg ) {
        return ResponseEntity.ok(ResultHelper.getSuccess(msg));
    }

    /**
     * 成功反馈
     * @param obj
     * @return
     */
    public ResponseEntity<Result> doSuccess(Object obj) {
        return ResponseEntity.ok(ResultHelper.getSuccess(obj));
    }

    /**
     * 成功过反馈
     * @return
     */
    public ResponseEntity<Result> doSuccess() {
        return ResponseEntity.ok(ResultHelper.getSuccess());
    }

    /**
     * 失败反馈
     * @return
     */
    public ResponseEntity<Result> doError(String msg) {
        return ResponseEntity.ok(ResultHelper.getError(msg));
    }

    /**
     * 失败反馈
     * @return
     */
    public ResponseEntity<Result> doError(IBaseEnum baseEnum) {
        return ResponseEntity.ok(ResultHelper.getError(baseEnum));
    }

    /**
     * 返回前台文件流
     *
     * @author fengshuonan
     * @date 2017年2月28日 下午2:53:19
     */
    protected ResponseEntity<byte[]> renderFile(String fileName, byte[] fileBytes) {
        String dfileName = null;
        try {
            dfileName = new String(fileName.getBytes("gb2312"), "iso8859-1");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
        headers.setContentDispositionFormData("attachment", dfileName);
        return new ResponseEntity<byte[]>(fileBytes, headers, HttpStatus.CREATED);
    }

    /**
     * 自动拼装分页
     * @param spage
     * @param <T>
     * @return
     */
    protected <T> Page<T> createPage(QueryPageEntity<T> spage)
    {
        if(spage!= null)
        {
            return new Page<T>(spage.getPage().getCurrent(),spage.getPage().getSize());
        }
        return null;
    }


}
