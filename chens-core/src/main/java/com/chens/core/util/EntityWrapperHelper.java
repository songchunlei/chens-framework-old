package com.chens.core.util;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.chens.core.exception.BaseException;
import com.chens.core.exception.BaseExceptionEnum;

import java.lang.reflect.Field;

/**
 * EntityWrapper工具类
 *
 * @auther songchunlei@qq.com
 * @create 2018/3/29
 */
public class EntityWrapperHelper {


    /**
     * 根据注解去自动解析EntityWrapper用于查询
     * @param object 查询值
     * @param isLike 是否模糊查询
     * @param isAnd 是否And查询
     * @param <T>
     * @return
     */
    public static <T> EntityWrapper<T> getQueryEntityWrapperByEntity(T object,boolean isLike,boolean isAnd)
    {
        EntityWrapper<T> wrapper = new EntityWrapper<T>();
        if (object!=null){
            //字段解析
            //**注意：这些字段不包含BaseEntity里的字段(id)**
            Field[] fields = object.getClass().getDeclaredFields();
            for (int i = 0; i < fields.length; i++) {

                //过滤掉UID
                if(fields[i].getName().equals("serialVersionUID"))
                {
                    continue;
                }
                try {
                    fields[i].setAccessible(true);
                    Object value = fields[i].get(object);
                    if (null != value && !value.equals("")) {
                        String fieldname = fields[i].getName();
                        //当有注解的时候采用注解
                        if(fields[i].isAnnotationPresent(TableField.class) )
                        {
                            TableField tableFieldName = (TableField)fields[i].getAnnotation(TableField.class);
                            if(tableFieldName!=null)
                            {
                                //当字段为不存在，不分析
                                if(!tableFieldName.exist())
                                {
                                    continue;
                                }
                                fieldname = tableFieldName.value();
                            }
                        }
                        //fieldname = StringUtils.underscoreName(fields[i].getName());
                        //当非and查询，则or查询
                        if(!isAnd)
                        {
                            wrapper.orNew();
                        }
                        if(isLike)
                        {
                            wrapper.like(fieldname,value.toString());
                        }
                        else
                        {
                            wrapper.eq(fieldname,value.toString());
                        }

                    }
                    fields[i].setAccessible(false);
                } catch (Exception e) {
                    throw new BaseException(BaseExceptionEnum.QUERY_ERROR);
                }
            }
        }
        return wrapper;
    }
}
