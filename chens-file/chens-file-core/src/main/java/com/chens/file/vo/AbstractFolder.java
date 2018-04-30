package com.chens.file.vo;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableLogic;
import com.chens.core.annotation.InsertValid;
import com.chens.core.annotation.UpdateValid;
import com.chens.core.enums.FileTypeEnum;
import com.chens.core.enums.YesNoEnum;
import com.chens.core.vo.BaseEntity;

import javax.validation.constraints.NotNull;

/**
 * <p>
 * 文件夹表
 * </p>
 *
 * @author wdp123
 * @since 2018-03-06
 */
public abstract class AbstractFolder<T extends AbstractFolder> extends BaseEntity<T> {

	@TableField(exist = false)
	private static final long serialVersionUID = 1815815883025805854L;

	/**
	 * 文件夹类型
	 */
	@TableField("type")
	protected String type ;

    /**
     * 等级
     */
    private Integer lvl;

    /**
     * 文件夹名称
     */
    @TableField("name")
    @NotNull(message = "{folder.folderName.null}",groups = {InsertValid.class, UpdateValid.class})
    private String name;

    /**
     * 上级文件夹id
     */
    @TableField("parent_id")
	@NotNull(message = "{folder.parentId.null}",groups = {InsertValid.class, UpdateValid.class})
    private String parentId;

	/**
	 * 语意id，默认启始1000000
	 */
	@TableField("cascade_id")
	private String cascadeId;

    /**
     * 备注
     */
    private String remark;

    /**
     * 是否删除
     */
	@TableField("is_delete")
	@TableLogic
    private String isDelete= YesNoEnum.NO.getCode();

	public String getType() {
		return this.type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getParentId() {
		return parentId;
	}
	public void setParentId(String parentId) {
		this.parentId = parentId;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public String getIsDelete() {
		return isDelete;
	}
	public void setIsDelete(String isDelete) {
		this.isDelete = isDelete;
	}

	public Integer getLvl() {
		return lvl;
	}

	public void setLvl(Integer lvl) {
		this.lvl = lvl;
	}

	public String getCascadeId() {
		return cascadeId;
	}

	public void setCascadeId(String cascadeId) {
		this.cascadeId = cascadeId;
	}

	public FolderFileInfo getFolderFileInfo()
	{
		return new FolderFileInfo(this.getId(),this.getParentId(), FileTypeEnum.FOLDER.getCode(),this.getLvl(),this.getName(), this.getUpdateTime());
	}
}
