package com.chens.tag.entity;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableName;
import com.chens.core.vo.BaseEntity;

/**
 *
 *  实体
 *
 * @author chunlei.song@live.com
 * @create 2018-04-27
 */
@TableName("t_tag_rel")
public class TagRel extends BaseEntity<TagRel> {

    private static final long serialVersionUID = 1L;

    /**
     * 数据id
     */
	@TableField("data_id")
	private String dataId;
	/**
	 * 关联标签id
	 */
	@TableField("tag_id")
	private String tagId;
	/**
	 * 是否删除
	 */
	@TableField("is_delete")
	private String isDelete;

	public TagRel(String dataId, String tagId) {
		this.dataId = dataId;
		this.tagId = tagId;
	}

	public TagRel() {
	}

	public String getIsDelete() {
		return isDelete;
	}

	public void setIsDelete(String isDelete) {
		this.isDelete = isDelete;
	}

	public String getTagId() {
		return tagId;
	}

	public void setTagId(String tagId) {
		this.tagId = tagId;
	}

	public String getDataId() {
		return dataId;
	}

	public void setDataId(String dataId) {
		this.dataId = dataId;
	}

}
