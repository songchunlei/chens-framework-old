package com.chens.core.entity;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableName;
import com.chens.core.vo.BaseEntity;

/**
 * <p>
 * 
 * </p>
 *
 * @author chunlei.song@live.com123
 * @since 2018-03-08
 */
@TableName("sys_dict_type")
public class SysDictType extends BaseEntity<SysDictType> {


    /**
	 * 
	 */
	private static final long serialVersionUID = 6195077992271624363L;

    @TableField("type_code")
    private String typeCode;
    @TableField("type_name")
    private String typeName;

    public String getTypeCode() {
        return typeCode;
    }

    public void setTypeCode(String typeCode) {
        this.typeCode = typeCode;
    }

    public String getTypeName() {
        return typeName;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }



}
