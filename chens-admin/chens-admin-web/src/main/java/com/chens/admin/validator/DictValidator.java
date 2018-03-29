package com.chens.admin.validator;

import com.chens.admin.entity.SysDict;
import com.chens.admin.service.ISysDictService;
import com.chens.core.util.ApplicationContextUtil;
import com.chens.core.validator.BaseValidator;

/**
 * 字典自定义校验
 *
 * @auther songchunlei@qq.com
 * @create 2018/3/29
 */
public class DictValidator extends BaseValidator<ISysDictService,SysDict>{

    //自定义初始化
    public DictValidator() {
        if(service==null)
        {
            service = ApplicationContextUtil.getBeanByClass(ISysDictService.class);
        }
    }

    public boolean checkUserNameUnique(String type) throws Exception {
        SysDict sysDict = new SysDict();
        sysDict.setType(type);
        return this.checkIsNotExist(sysDict);
    }
}
