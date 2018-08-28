package com.chens.admin.entity;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableName;
import com.chens.core.annotation.InsertValid;
import com.chens.core.annotation.MyValidator;
import com.chens.core.annotation.UpdateValid;
import com.chens.core.vo.BaseEntity;

import javax.validation.constraints.NotNull;

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

	@NotNull(message = "{dictType.code.null}")
    @TableField("type_code")
    private String typeCode;

    @NotNull(message = "{dictType.name.null}")
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
