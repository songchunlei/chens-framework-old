package com.chens.file.vo;

import java.io.Serializable;

/**
 * 文件数据
 *
 * @author songchunlei@qq.com
 * @create 2018/4/17
 */
public class FileData implements Serializable{

    /**
     * 文件组id
     */
    private String groupId;

    /**
     * 文件名称
     */
    private String name;

    /**
     * 类型
     */
    private String type;

    /**
     * 大小
     */
    private Long size;

    /**
     * 文件数据
     */
    private byte[] data;

    /**
     * 描述
     */
    private String descText;

    public FileData() {
    }

    public FileData(String groupId, String name, String type, Long size, byte[] data, String descText) {
        this.groupId = groupId;
        this.name = name;
        this.type = type;
        this.size = size;
        this.data = data;
        this.descText = descText;
    }

    public String getGroupId() {
        return groupId;
    }

    public void setGroupId(String groupId) {
        this.groupId = groupId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    public byte[] getData() {
        return data;
    }

    public void setData(byte[] data) {
        this.data = data;
    }

    public String getDescText() {
        return descText;
    }

    public void setDescText(String descText) {
        this.descText = descText;
    }
}
