package com.chens.tag.entity;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableName;
import com.chens.core.vo.BaseEntity;

/**
 *
 *  实体
 *
 * @author chunlei.song@live.com
 * @create 2018-04-15
 */
@TableName("t_tag_class")
public class TagClass extends BaseEntity<TagClass> {

    private static final long serialVersionUID = 1L;

    /**
     * 分类名称
     */
	@TableField("class_name")
	private String className;
    /**
     * 备注
     */
	private String comment;


	public String getClassName() {
		return className;
	}

	public void setClassName(String className) {
		this.className = className;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

}
