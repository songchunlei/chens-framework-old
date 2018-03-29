package com.chens.admin.entity;

import com.baomidou.mybatisplus.annotations.TableName;
import com.chens.core.vo.BaseEntity;

/**
 *
 *  实体
 *
 * @author chunlei.song@live.com
 * @create 2018-03-24
 */
@TableName("sys_log")
public class SysLog extends BaseEntity<SysLog> {

    private static final long serialVersionUID = 1L;

    /**
     * 操作类型
     */
	private String opt;
    /**
     * 请求人地址
     */
	private String host;
    /**
     * 请求串
     */
	private String url;


	public String getOpt() {
		return opt;
	}

	public void setOpt(String opt) {
		this.opt = opt;
	}

	public String getHost() {
		return host;
	}

	public void setHost(String host) {
		this.host = host;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

}
