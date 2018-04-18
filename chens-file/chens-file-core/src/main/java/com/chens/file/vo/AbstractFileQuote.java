package com.chens.file.vo;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableName;
import com.chens.core.vo.BaseEntity;

/**
 *
 * 数据-引用文件关系表 实体
 *
 * @author chunlei.song@live.com
 * @create 2018-04-18
 */
public abstract class AbstractFileQuote<T extends AbstractFileQuote> extends BaseEntity<T> {

    private static final long serialVersionUID = 1L;

    /**
     * 数据类型
     */
	private String type;
    /**
     * 对应数据id
     */
	@TableField("data_id")
	private String dataId;
    /**
     * 排序
     */
	private Integer seq;
    /**
     * 资源id
     */
	@TableField("file_id")
	private String fileId;
    /**
     * 文件抽象类型
     */
	@TableField("file_type")
	private String fileType;
    /**
     * 文件真实类型
     */
	@TableField("file_ext")
	private String fileExt;
    /**
     * 资源地址(冗余字段)
     */
	@TableField("file_path")
	private String filePath;
    /**
     * 备注
     */
	private String remark;
    /**
     * 逻辑删除
     */
	@TableField("is_delete")
	private String isDelete;


	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getDataId() {
		return dataId;
	}

	public void setDataId(String dataId) {
		this.dataId = dataId;
	}

	public Integer getSeq() {
		return seq;
	}

	public void setSeq(Integer seq) {
		this.seq = seq;
	}

	public String getFileId() {
		return fileId;
	}

	public void setFileId(String fileId) {
		this.fileId = fileId;
	}

	public String getFileType() {
		return fileType;
	}

	public void setFileType(String fileType) {
		this.fileType = fileType;
	}

	public String getFileExt() {
		return fileExt;
	}

	public void setFileExt(String fileExt) {
		this.fileExt = fileExt;
	}

	public String getFilePath() {
		return filePath;
	}

	public void setFilePath(String filePath) {
		this.filePath = filePath;
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

	public String getUpdateByName() {
		return updateByName;
	}

	public void setUpdateByName(String updateByName) {
		this.updateByName = updateByName;
	}

	public String getCreateByName() {
		return createByName;
	}

	public void setCreateByName(String createByName) {
		this.createByName = createByName;
	}

}
