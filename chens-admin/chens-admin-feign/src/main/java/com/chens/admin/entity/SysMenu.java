package com.chens.admin.entity;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableName;
import com.chens.core.annotation.InsertValid;
import com.chens.core.annotation.UpdateValid;
import com.chens.core.vo.BaseEntity;

import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * <p>
 * 菜单
 * </p>
 *
 * @author chunlei.song@live.com123
 * @since 2018-03-09
 */
@TableName("sys_menu")
public class SysMenu extends BaseEntity<SysMenu> {

    private static final long serialVersionUID = 1L;

    @TableField("parent_id")
    @NotNull(message = "{menu.parentId.null}",groups = {InsertValid.class, UpdateValid.class})
    private String parentId;

    @NotNull(message = "{menu.type.null}",groups = {InsertValid.class, UpdateValid.class})
    private String type;

    @NotNull(message = "{menu.name.null}",groups = {InsertValid.class, UpdateValid.class})
    private String name;

    /**
     * 访问地址
     */
    private String url;
    /**
     * 描述
     */
    private String description;
    /**
     * 排序
     */
    @NotNull(message = "{menu.seq.null}",groups = {InsertValid.class, UpdateValid.class})
    private Integer seq;

    /**
     * 是否打开 1打开 0不打开
     */
    private Integer isopen;
    /**
     * 图标
     */
    private String icon;

    /**
     * 子菜单
     */
    @TableField(exist = false)
    private List<SysMenu> childList;

    /**
     * 菜单编码
     */
    @NotNull(message = "{menu.code.null}",groups = {InsertValid.class, UpdateValid.class})
    private String code;


    public String getParentId() {
        return parentId;
    }

    public void setParentId(String parentId) {
        this.parentId = parentId;
    }

    public String getType() {
        return type;
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

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getSeq() {
        return seq;
    }

    public void setSeq(Integer seq) {
        this.seq = seq;
    }

    public Integer getIsopen() {
        return isopen;
    }

    public void setIsopen(Integer isopen) {
        this.isopen = isopen;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public List<SysMenu> getChildList() {
        return childList;
    }

    public void setChildList(List<SysMenu> childList) {
        this.childList = childList;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }
}
