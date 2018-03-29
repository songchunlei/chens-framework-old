package com.chens.core.validator;

import com.baomidou.mybatisplus.service.IService;
import com.chens.core.annotation.UniqueSysUserName;
import com.chens.core.exception.BaseException;
import com.chens.core.exception.BaseExceptionEnum;
import com.chens.core.util.AopTargetUtil;
import com.chens.core.util.ApplicationContextUtil;
import com.chens.core.util.SpringBeanFactoryUtils;
import com.chens.core.vo.BaseEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.ContextLoader;
import org.springframework.web.context.WebApplicationContext;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.lang.reflect.InvocationTargetException;

/**
 * 唯一性校验
 *
 * @auther songchunlei@qq.com
 * @create 2018/3/28
 */
@Component
public class UniqueSysUserNameValidator implements ConstraintValidator<UniqueSysUserName,Object>{

    /**
     *
     * //1.错误方法：通过反射执行service的方法
     String serviceClass = templateInfo.getService();//service执行类的名称
     String method = templateInfo.getMethod();//调用方法名
     //根据反射执行保存操作
     Class<?> classType = Class.forName(serviceClass);
     Method m = classType.getDeclaredMethod(method,new Class[]{PageData.class});
     m.invoke(classType.newInstance(),pd);


     //2.解决方法:通过获取Spring容器取得对象：
     WebApplicationContext wac = ContextLoader.getCurrentWebApplicationContext();
     DivStattisTabService service = (DivStattisTabService)
     Class<?>  cls = wac.getBean("divstattistabService").getClass();
     Method m = classType.getDeclaredMethod(method,new Class[]{PageData.class});
     m.invoke(wac.getBean("divstattistabService"),pd);
     //注：m.invoke方法第一个参数不能使用newInstance方法，否则Service中dao的注入失败，dao为null
     */


    private String className = "";
    private String methodName="";



    @Override
    public void initialize(UniqueSysUserName constraintAnnotation) {
        className = constraintAnnotation.serviceClass();
        methodName = constraintAnnotation.methodName();
        System.out.println("ceshi-init");
    }

    @Override
    public boolean isValid(Object value, ConstraintValidatorContext context) {
        try {

            Class<?> clazz = Class.forName(className);

            Object instance = clazz.newInstance();

            //如果是被代理对象，获取实例化对象
            //Object newInstance = AopTargetUtil.getTarget(clazz);

            //直接调用方法
            boolean flg = (boolean) clazz.getMethod(methodName).invoke(instance,null);



            //Object instance = SpringBeanFactoryUtils.getBean(className);
            //Class newClazz = ApplicationContextUtil.getBeanByClass(clazz);

            //Object instance = newClazz.newInstance();
            //加载bean
            //Object instance = SpringBeanFactoryUtils.getBean(className);
            //boolean flg = (boolean) newClazz.getMethod(methodName).invoke(instance,null);

        } catch (ClassNotFoundException e) {
            //e.printStackTrace();
            throw new BaseException(BaseExceptionEnum.REQUEST_NULL);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println(value);
        return false;
    }


}
