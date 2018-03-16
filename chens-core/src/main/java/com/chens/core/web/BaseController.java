package com.chens.core.web;

import java.io.UnsupportedEncodingException;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.chens.core.enums.IBaseEnum;
import com.chens.core.exception.BaseException;
import com.chens.core.exception.BaseExceptionEnum;
import com.chens.core.util.FileUtil;
import com.fasterxml.jackson.databind.deser.Deserializers;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;

import com.baomidou.mybatisplus.plugins.Page;
import com.chens.core.util.ResultHelper;
import com.chens.core.vo.Result;

/**
 * 基础web包
 *
 * @auther songchunlei@qq.com
 * @create 2018/3/5
 */
public class BaseController {

    private Class<?> clazz;
    protected Logger logger;
    protected HttpServletRequest request;
    protected HttpServletResponse response;

//    private final String EMPTY_MSG = "";


    protected BaseController() {
        clazz = getSuperClassGenricType(getClass(), 0);
        logger = LoggerFactory.getLogger(clazz);
    }


    /**
     * 通过反射,获得定义Class时声明的父类的范型参数的类型
     *
     * @param clazz The class to introspect
     * @param index the Index of the generic declaration,start from 0.
     */
    private Class<?> getSuperClassGenricType(Class<?> clazz, int index)
            throws IndexOutOfBoundsException {
        Type genType = clazz.getGenericSuperclass();
        if (!(genType instanceof ParameterizedType)) {
            return clazz;
        }

        Type[] params = ((ParameterizedType) genType).getActualTypeArguments();
        if (index >= params.length || index < 0) {
            return clazz;
        }
        if (!(params[index] instanceof Class)) {
            return clazz;
        }

        return (Class<?>) params[index];
    }

    /**
     * 获取工程根目录真实路径
     */
    public String getRealPath() {
        return request.getSession().getServletContext().getRealPath("/");
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
    protected ResponseEntity<byte[]> renderFile(String fileName, String filePath) {
        byte[] bytes = FileUtil.toByteArray(filePath);
        return renderFile(fileName, bytes);
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


}
