package com.chens.core.annotation;

import com.chens.core.validator.BaseValidatorHandler;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

/**
 * 唯一性校验
 * @author songchunlei
 * @create 2018/3/28
 */
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy =BaseValidatorHandler.class )
@Documented
public @interface MyValidator {

    String message() default "";
    //当前被校验class
    Class<?> thisclass();
    //当前调用的校验服务class
    String serviceClass() default "";
    //当前调用的校验服务class上的方法
    String methodName() default "";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

    //指定多个时使用
    @Target({ ElementType.FIELD, ElementType.METHOD, ElementType.PARAMETER, ElementType.ANNOTATION_TYPE })
    @Retention(RetentionPolicy.RUNTIME)
    @Documented
    @interface List {
        MyValidator[] value();
    }
}
