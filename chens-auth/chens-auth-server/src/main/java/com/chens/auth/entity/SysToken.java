package com.chens.auth.entity;

import java.util.Date;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.enums.IdType;
import com.baomidou.mybatisplus.annotations.TableName;
import com.chens.core.vo.BaseEntity;

/**
 *
 *  实体
 *
 * @author chunlei.song@live.com
 * @create 2018-03-25
 */
@TableName("sys_token")
public class SysToken extends BaseEntity<SysToken> {

    private static final long serialVersionUID = 1L;

	/**
	 * 用户id
	 */
	@TableField("user_id")
	private String userId;

    /**
     * token
     */
	private String token;
    /**
     * 失效时间
     */
	@TableField("invalid_time")
	private Date invalidTime;
    /**
     * 是否有效
     */
	@TableField("is_delete")
	private String isDelete;

	public SysToken() {
	}

	public SysToken(String token, Date invalidTime, String isDelete) {
		this.token = token;
		this.invalidTime = invalidTime;
		this.isDelete = isDelete;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public Date getInvalidTime() {
		return invalidTime;
	}

	public void setInvalidTime(Date invalidTime) {
		this.invalidTime = invalidTime;
	}

	public String getIsDelete() {
		return isDelete;
	}

	public void setIsDelete(String isDelete) {
		this.isDelete = isDelete;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}
}
