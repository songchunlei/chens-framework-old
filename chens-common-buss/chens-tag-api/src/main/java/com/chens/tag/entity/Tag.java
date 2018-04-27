package com.chens.tag.entity;

import java.util.Date;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.enums.IdType;
import com.baomidou.mybatisplus.annotations.TableName;
import com.chens.core.vo.BaseEntity;

/**
 *
 *  实体
 *
 * @author chunlei.song@live.com
 * @create 2018-04-27
 */
@TableName("t_tag")
public class Tag extends BaseEntity<Tag> {

    private static final long serialVersionUID = 1L;

    /**
     * 描述
     */
	private String comment;
    /**
     * 标签名称
     */
	@TableField("tag_name")
	private String tagName;
    /**
     * 类别
     */
	private String type;
    /**
     * 标签分类
     */
	@TableField("class_id")
	private String classId;


	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public String getTagName() {
		return tagName;
	}

	public void setTagName(String tagName) {
		this.tagName = tagName;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getClassId() {
		return classId;
	}

	public void setClassId(String classId) {
		this.classId = classId;
	}

}
