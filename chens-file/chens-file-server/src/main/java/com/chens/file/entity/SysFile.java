package com.chens.file.entity;

import java.util.Date;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.enums.IdType;
import com.baomidou.mybatisplus.annotations.TableName;
import com.chens.core.vo.BaseEntity;

import java.io.Serializable;

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
     * 大小
     */
    private Long size;

    public SysFile() {
    }

    public SysFile(String name, Long size,String md5) {
        this.name = name;
        this.size = size;
        this.md5 = md5;
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

    public Long getSize() {
        return size;
    }

    public void setSize(Long size) {
        this.size = size;
    }

}
