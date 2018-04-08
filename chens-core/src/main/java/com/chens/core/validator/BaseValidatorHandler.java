package com.chens.core.validator;

import java.lang.reflect.InvocationTargetException;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import com.chens.core.annotation.MyValidator;

/**
 * 唯一性校验
 *
 * @auther songchunlei@qq.com
 * @create 2018/3/28
 */
public class BaseValidatorHandler implements ConstraintValidator<MyValidator,Object>{

    private Class<?> clazz;
    private String serviceClassName = "";
    private String methodName="";



    @Override
    public void initialize(MyValidator constraintAnnotation) {
        clazz = constraintAnnotation.thisclass();
        serviceClassName = constraintAnnotation.serviceClass();
        methodName = constraintAnnotation.methodName();
    }

    @Override
    public boolean isValid(Object value, ConstraintValidatorContext context) {

        Class<?> serviceClazz ;
        //Class<?> clazz ;
        try {
            serviceClazz = Class.forName(serviceClassName);
            clazz = Class.forName(serviceClassName);
            Object instance = serviceClazz.newInstance();
            return (boolean) serviceClazz.getMethod(methodName,clazz).invoke(instance, value);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }


}
