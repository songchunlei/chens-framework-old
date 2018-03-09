package com.chens.core.handler;

import com.baomidou.mybatisplus.mapper.MetaObjectHandler;
import com.chens.core.vo.UserInfo;
import com.chens.core.enums.YesNoEnum;
import org.apache.ibatis.reflection.MetaObject;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;

import java.util.Date;


/**
 *   自定义填充公共 创建人，创建时间，修改人、修改时间  字段  
 * @author WDP
 *
 */
@Component
public class MyMetaObjectHandler extends MetaObjectHandler {
	
	 private Logger logger = LogManager.getLogger(MyMetaObjectHandler.class);

	@Override
	public void insertFill(MetaObject metaObject) {
		logger.info("*****************保存自动填充******************");
        Date date = new Date();
        
        //用户信息需要后期统一修改
        UserInfo userInfo = new UserInfo();
        userInfo.setUsername("wudepeng");
        userInfo.setTenantId(1l);
        
        
        setFieldValByName("createTime", date, metaObject);
        setFieldValByName("updateTime", date, metaObject); 
        setFieldValByName("createBy", userInfo.getUsername(), metaObject);
        setFieldValByName("updateBy", userInfo.getUsername(), metaObject);
        setFieldValByName("tennatId", userInfo.getTenantId(), metaObject);
        setFieldValByName("isDelete", YesNoEnum.NO.getCode(), metaObject);
        
	}

	@Override
	public void updateFill(MetaObject metaObject) {
		 //更新填充
        logger.info("********************更新自动填充********************");
        //mybatis-plus版本2.0.9+  
        Date date = new Date();
        //用户信息需要后期统一修改
        UserInfo userInfo = new UserInfo();
        userInfo.setUsername("wdp");

        setFieldValByName("updateTime", date, metaObject);
        setFieldValByName("updateBy", userInfo.getUsername(), metaObject);

	}

}
