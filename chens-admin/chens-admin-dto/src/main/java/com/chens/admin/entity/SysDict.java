package com.chens.admin.entity;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableName;
import com.chens.core.annotation.InsertValid;
import com.chens.core.annotation.UpdateValid;
import com.chens.core.vo.BaseEntity;

import javax.validation.constraints.NotNull;

/**
 *
 *  实体
 *
 * @author chunlei.song@live.com
 * @create 2018-03-12
 */
@TableName("sys_dict")
public class SysDict extends BaseEntity<SysDict> {

    private static final long serialVersionUID = 1L;

    @NotNull(message = "{dict.type.null}",groups = {InsertValid.class, UpdateValid.class})
	private String type;
	@NotNull(message = "{dict.val.null}",groups = {InsertValid.class, UpdateValid.class})
	private String val;
	@NotNull(message = "{dict.text.null}",groups = {InsertValid.class, UpdateValid.class})
	private String text;
	@NotNull(message = "{dict.seq.null}",groups = {InsertValid.class, UpdateValid.class})
	private Integer seq;
	@TableField("DESCRIPTION")
	private String description;
	@NotNull(message = "{dict.parentId.null}",groups = {InsertValid.class, UpdateValid.class})
	@TableField("PARENT_ID")
	private String parentId;
	@TableField("is_delete")
	private String isDelete;
	private String exp1;
	private String exp2;


	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getVal() {
		return val;
	}

	public void setVal(String val) {
		this.val = val;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public Integer getSeq() {
		return seq;
	}

	public void setSeq(Integer seq) {
		this.seq = seq;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getParentId() {
		return parentId;
	}

	public void setParentId(String parentId) {
		this.parentId = parentId;
	}

	public String getIsDelete() {
		return isDelete;
	}

	public void setIsDelete(String isDelete) {
		this.isDelete = isDelete;
	}

	public String getExp1() {
		return exp1;
	}

	public void setExp1(String exp1) {
		this.exp1 = exp1;
	}

	public String getExp2() {
		return exp2;
	}

	public void setExp2(String exp2) {
		this.exp2 = exp2;
	}

}
