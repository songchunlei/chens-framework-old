package com.chens.bpm.vo;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.enums.IdType;
import com.chens.core.vo.BaseEntity;

/**
 * 流程模块基础实体类
 *
 * @auther songchunlei@qq.com
 * @create 2018/3/30
 */
public class WfBaseEntity <T extends WfBaseEntity> extends BaseEntity<T>{

    /**
     * 流程状态
     */
    protected String status;

    /**
     * 流程实例id
     */
    @TableField(value = "wf_inst_id")
    protected String wfInstId;

    /**
     * 流程定义key
     */
    @TableField(value = "wf_define_key")
    protected String wfDefineKey;


    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getWfInstId() {
        return wfInstId;
    }

    public void setWfInstId(String wfInstId) {
        this.wfInstId = wfInstId;
    }

    public String getWfDefineKey() {
        return wfDefineKey;
    }

    public void setWfDefineKey(String wfDefineKey) {
        this.wfDefineKey = wfDefineKey;
    }
}
