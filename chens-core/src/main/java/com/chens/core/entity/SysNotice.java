package com.chens.core.entity;

import com.baomidou.mybatisplus.annotations.TableName;
import com.chens.core.vo.BaseEntity;

import javax.validation.constraints.NotNull;

/**
 *
 *  实体
 *
 * @author chunlei.song@live.com
 * @create 2018-03-12
 */
@TableName("sys_notice")
public class SysNotice extends BaseEntity<SysNotice> {

    private static final long serialVersionUID = 1L;

    /**
     * 标题
     */
    @NotNull
	private String title;
    /**
     * 类型
     */
	@NotNull
	private String type;
    /**
     * 内容
     */
	@NotNull
	private String content;


	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

}
