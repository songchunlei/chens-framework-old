package com.chens.core.annotation;

import com.chens.core.validator.UniqueValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @auther songchunlei
 * @create 2018/3/28
 */
@Target({ElementType.METHOD, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy =UniqueValidator.class )
public @interface UniqueField {

    String message() default "测试";
    String serviceClass() default "服务名";
    String methodName() default "方法名";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
