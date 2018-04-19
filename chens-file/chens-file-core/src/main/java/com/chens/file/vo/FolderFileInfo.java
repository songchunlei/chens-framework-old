package com.chens.file.vo;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * 文件夹列表抽象
 * 文件夹下文件/文件夹信息
 * @author songchunlei@qq.com
 * @create 2018/4/9
 */
public class FolderFileInfo implements Serializable{

    /**
     * id
     */
    private String id;

    /**
     * 文件名
     */
    private String name;

    /**
     * 文件夹累心
     */
    private String type;

    /**
     * 目录等级
     */
    private Integer lvl;

    /**
     * 最后更新时间
     */
    private Date updateTime;

    /**
     * 父文件夹
     */
    private FolderFileInfo parent;

    /**
     * 文件夹下的子文件夹
     */
    private List<FolderFileInfo> children;

    public FolderFileInfo() {
    }

    public FolderFileInfo(String id, String type, String name, Date updateTime) {
        this.id = id;
        this.type = type;
        this.name = name;
        this.updateTime = updateTime;
    }

    public FolderFileInfo(String id,String type,Integer lvl,  String name, Date updateTime) {
        this.id = id;
        this.lvl = lvl;
        this.type = type;
        this.name = name;
        this.updateTime = updateTime;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getLvl() {
        return lvl;
    }

    public void setLvl(Integer lvl) {
        this.lvl = lvl;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public FolderFileInfo getParent() {
        return parent;
    }

    public void setParent(FolderFileInfo parent) {
        this.parent = parent;
    }

    public List<FolderFileInfo> getChildren() {
        return children;
    }

    public void setChildren(List<FolderFileInfo> children) {
        this.children = children;
    }
}
