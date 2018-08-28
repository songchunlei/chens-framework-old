package com.chens.admin.validator;

import com.chens.admin.entity.SysDictType;
import com.chens.admin.service.ISysDictTypeService;
import com.chens.core.util.ApplicationContextUtil;
import com.chens.core.validator.BaseValidator;

/**
 * 字典自定义校验
 *
 * @auther songchunlei@qq.com
 * @create 2018/3/29
 */
public class DictValidator extends BaseValidator<ISysDictTypeService,SysDictType>{

    //自定义初始化
    public DictValidator() {
        if(service==null)
        {
            service = ApplicationContextUtil.getBeanByClass(ISysDictTypeService.class);
        }
    }

    public boolean check(SysDictType sysDictType) throws Exception {
        SysDictType query = new SysDictType();
        query.setTypeCode(sysDictType.getTypeCode());
        query.setId(sysDictType.getId());
        return this.checkIsNotExist(sysDictType);
    }
}
