package com.chens.core.entity;

import com.baomidou.mybatisplus.annotations.TableName;
import com.chens.core.vo.BaseEntity;

/**
 * <p>
 * 
 * </p>
 *
 * @author chunlei.song@live.com
 * @since 2018-03-09
 */
@TableName("sys_notice")
public class SysNotice extends BaseEntity<SysNotice> {

    private static final long serialVersionUID = 1L;
    /**
     * 标题
     */
    private String title;
    /**
     * 类型
     */
    private Long type;
    /**
     * 内容
     */
    private String content;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Long getType() {
        return type;
    }

    public void setType(Long type) {
        this.type = type;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
