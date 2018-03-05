package com.chens.core.web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.chens.core.entity.Result;
import com.chens.core.util.ResultHelper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
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
     * 返回成功处理请求
     * @param t
     * @param <T>
     * @return
     */
    public <T> ResponseEntity<T> doOk(T t) {
        return new ResponseEntity<T>(t, HttpStatus.OK);
    }

    /**
     * 错误反馈通用方法
     * @param code
     * @param msg
     * @return
     */
    public ResponseEntity<Result> doError(int code,String msg) {
        return doOk(ResultHelper.getError(code,msg));
    }

    /**
     * 错误反馈
     * @param code
     * @return
     */
    public ResponseEntity<Result> doError(int code) {
        return doOk(ResultHelper.getError(code));
    }

    /**
     * 错误反馈
     * @param msg
     * @return
     */
    public ResponseEntity<Result> doError(String  msg) {
        return doOk(ResultHelper.getError(msg));
    }

    /**
     * 错误反馈
     * @return
     */
    public ResponseEntity<Result> doError() {
        return doOk(ResultHelper.getError());
    }

    /**
     * 成功反馈通用方法
     * @param msg
     * @param obj
     * @return
     */
    public ResponseEntity<Result> doSuccess(String msg ,Object obj) {
        return doOk(ResultHelper.getSuccess(msg ,obj));
    }

    /**
     * 成功反馈
     * @param msg
     * @return
     */
    public ResponseEntity<Result> doSuccess(String msg ) {
        return doOk(ResultHelper.getSuccess(msg ));
    }

    /**
     * 成功反馈
     * @param obj
     * @return
     */
    public ResponseEntity<Result> doSuccess(Object obj) {
        return doOk(ResultHelper.getSuccess(obj ));
    }

    /**
     * 成功过反馈
     * @return
     */
    public ResponseEntity<Result> doSuccess() {
        return doOk(ResultHelper.getSuccess( ));
    }

}
