package com.chens.file.entity;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableName;
import com.chens.core.vo.BaseEntity;
import com.chens.file.vo.FileData;

/**
 * <p>
 * 
 * </p>
 *
 * @author chunlei.song@live.com123
 * @since 2018-03-09
 */
@TableName("sys_file")
public class SysFile extends BaseEntity<SysFile> {

    private static final long serialVersionUID = 1L;

    /**
     * 文件名
     */
    @TableField("original_name")
    private String originalName;
    /**
     * 文件名
     */
    private String name;
    /**
     * 路径
     */
    private String url;
    /**
     * md5
     */
    private String md5;
    /**
     * 标签
     */
    private String tag;
    /**
     * 类型
     */
    private String type;
    /**
     * 大小
     */
    private Long size;
    /**
     * 文件组
     */
    @TableField("group_id")
    private String groupId;
    /**
     * 是否删除
     */
    @TableField("is_delete")
    private String isDelete;

    public SysFile() {
    }

    /**
     * 保存文件
     * @param originalName
     * @param name
     * @param url
     * @param md5
     * @param tag
     * @param size
     */
    public SysFile(String originalName, String name, String url, String md5, String tag, Long size) {
        this.originalName = originalName;
        this.name = name;
        this.url = url;
        this.md5 = md5;
        this.tag = tag;
        this.size = size;
    }

    /**
     * 根据文件数据初始化新文件信息
     * @param fileData
     */
    public SysFile(FileData fileData,String url) {
        this.originalName = fileData.getOrgName();
        this.name = fileData.getName();
        this.url = url;
        this.md5 = fileData.getMd5();
        this.size = fileData.getSize();
        this.type = fileData.getType();
        this.groupId = fileData.getGroupId();
    }

    public String getOriginalName() {
        return originalName;
    }

    public void setOriginalName(String originalName) {
        this.originalName = originalName;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getMd5() {
        return md5;
    }

    public void setMd5(String md5) {
        this.md5 = md5;
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Long getSize() {
        return size;
    }

    public void setSize(Long size) {
        this.size = size;
    }

    public String getGroupId() {
        return groupId;
    }

    public void setGroupId(String groupId) {
        this.groupId = groupId;
    }

    public String getIsDelete() {
        return isDelete;
    }

    public void setIsDelete(String isDelete) {
        this.isDelete = isDelete;
    }
}
