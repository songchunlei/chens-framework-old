package com.chens.core.entity;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.annotations.TableLogic;
import com.chens.core.vo.BaseEntity;

/**
 * <p>
 * 
 * </p>
 *
 * @author chunlei.song@live.com
 * @since 2018-03-09
 */
@TableName("sys_dict")
public class SysDict extends BaseEntity<SysDict> {

    private static final long serialVersionUID = 6024090447179312099L;

    private String type;
    private String val;
    private String text;
    private Integer seq;
    @TableField("DESCRIPTION")
    private String description;
    @TableField("PARENT_ID")
    private Long parentId;
    @TableField("is_delete")
    @TableLogic
    private String isDelete;
    private String exp1;
    private String exp2;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getVal() {
        return val;
    }

    public void setVal(String val) {
        this.val = val;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Integer getSeq() {
        return seq;
    }

    public void setSeq(Integer seq) {
        this.seq = seq;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Long getParentId() {
        return parentId;
    }

    public void setParentId(Long parentId) {
        this.parentId = parentId;
    }

    public String getIsDelete() {
        return isDelete;
    }

    public void setIsDelete(String isDelete) {
        this.isDelete = isDelete;
    }

    public String getExp1() {
        return exp1;
    }

    public void setExp1(String exp1) {
        this.exp1 = exp1;
    }

    public String getExp2() {
        return exp2;
    }

    public void setExp2(String exp2) {
        this.exp2 = exp2;
    }

}
