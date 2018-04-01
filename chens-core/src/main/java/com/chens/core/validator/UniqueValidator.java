package com.chens.core.validator;

import java.lang.reflect.InvocationTargetException;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import com.chens.core.annotation.UniqueField;

/**
 * 唯一性校验
 *
 * @auther songchunlei@qq.com
 * @create 2018/3/28
 */
public class UniqueValidator implements ConstraintValidator<UniqueField,Object>{

    private String className = "";
    private String methodName="";



    @Override
    public void initialize(UniqueField constraintAnnotation) {
        className = constraintAnnotation.serviceClass();
        methodName = constraintAnnotation.methodName();
    }

    @Override
    public boolean isValid(Object value, ConstraintValidatorContext context) {

        Class<?> clazz ;
        try {
            clazz = Class.forName(className);
            Object instance = clazz.newInstance();
            return (boolean) clazz.getMethod(methodName,String.class).invoke(instance, value);
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
