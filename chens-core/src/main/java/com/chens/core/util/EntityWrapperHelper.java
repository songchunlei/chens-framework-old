package com.chens.core.util;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.chens.core.constants.CommonConstants;
import com.chens.core.context.BaseContextHandler;
import com.chens.core.exception.BaseException;
import com.chens.core.exception.BaseExceptionEnum;
import com.chens.core.vo.PageVo;

import java.lang.reflect.Field;

/**
 * EntityWrapper工具类
 *
 * @author songchunlei@qq.com
 * @create 2018/3/29
 */
public class EntityWrapperHelper {


    /**
     * 根据注解去自动解析EntityWrapper用于查询
     * @param object 查询值
     * @param pageVo 分页属性
     * @param <T>
     * @return
     */
    public static <T> EntityWrapper<T> getQueryEntityWrapperByEntity(T object, PageVo pageVo)
    {
        boolean isLike = pageVo.isLike();
        boolean isAnd = pageVo.isAnd();
        EntityWrapper<T> wrapper = new EntityWrapper<T>();
        //加入排序字段
        wrapper.orderBy(pageVo.getOrderByField());
        //是否只查自己
        if(pageVo.isMy())
        {
            if(StringUtils.isNotEmpty(BaseContextHandler.getUserId()))
            {
                wrapper.eq(CommonConstants.BASE_ENTITY_CREATE_BY, BaseContextHandler.getUserId());
            }
        }
        //是否查租户
        if(pageVo.isTenant())
        {
            TableName tableName = object.getClass().getAnnotation(TableName.class);
            if(tableName!=null && StringUtils.isNotEmpty(BaseContextHandler.getTenantId()))
            {
                //只在非租户表生效该查询条件
                boolean flg = CommonConstants.NO_TENANT_TABLENAME.indexOf("["+tableName+"]")!=-1;
                if (flg) {
                    wrapper.eq(CommonConstants.BASE_COLUMN_TENANT_ID, BaseContextHandler.getTenantId());
                }
            }
        }


        if (object!=null){
            //字段解析
            //**注意：这些字段不包含BaseEntity里的字段(id)**
            Field[] fields = object.getClass().getDeclaredFields();
            for (int i = 0; i < fields.length; i++) {

                //过滤掉UID
                if("serialVersionUID".equals(fields[i].getName()))
                {
                    continue;
                }
                try {
                    fields[i].setAccessible(true);
                    Object value = fields[i].get(object);
                    if (null != value && !value.equals("")) {
                        String fieldName = fields[i].getName();
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
                                fieldName = tableFieldName.value();
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
                            wrapper.like(fieldName,value.toString());
                        }
                        else
                        {
                            wrapper.eq(fieldName,value.toString());
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
