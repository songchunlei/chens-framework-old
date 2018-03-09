package com.chens.core.web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.chens.core.vo.Result;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

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

    private final String EMPTY_MSG = "";


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
    public <T> ResponseEntity<Result> doSuccess(String msg ,T obj) {
        return ResponseEntity.ok(new Result(msg,obj));
    }

    /**
     * 成功反馈
     * @param msg
     * @return
     */
    public ResponseEntity<Result> doSuccess(String msg ) {
        return doSuccess(msg,null);
    }

    /**
     * 成功反馈
     * @param obj
     * @return
     */
    public <T> ResponseEntity<Result> doSuccess(T obj) {
        return doSuccess(EMPTY_MSG,obj);
    }

    /**
     * 成功过反馈
     * @return
     */
    public ResponseEntity<Result> doSuccess() {
        return doSuccess(EMPTY_MSG, null);
    }

}
