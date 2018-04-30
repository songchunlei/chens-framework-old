package com.chens.share.entity;

import java.util.Date;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.enums.IdType;
import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.annotations.TableLogic;
import com.chens.core.vo.BaseEntity;
import com.chens.share.enums.ShareTypeEnum;

import javax.validation.constraints.NotNull;

/**
 *
 * 站内分享 实体
 *
 * @author chunlei.song@live.com
 * @create 2018-04-29
 */
@TableName("t_share")
public class Share extends BaseEntity<Share> {

    private static final long serialVersionUID = 1L;

	@TableField("is_delete")
	@TableLogic
	private String isDelete;
    /**
     * 分享类型，组织分享，用户分享,密码分享
     */
    @NotNull
	@TableField("share_type")
	private String shareType;
    /**
     * 分享人id
     */
	@NotNull
	@TableField("share_by")
	private String shareBy;
    /**
     * 被分享人id
     */
	@TableField("share_to_user")
	private String shareToUser;

	/**
	 * 数据类型，自定义
	 */
	@TableField("data_type")
	private String dataType;

    /**
     * 数据id
     */
    @NotNull
	@TableField("data_id")
	private String dataId;
    /**
     * 被分享组织id
     */
	@TableField("share_to_org")
	private String shareToOrg;
    /**
     * 密码分享
     */
	@TableField("share_pwd")
	private String sharePwd;

	/**
	 * 失效时间
	 */
	@TableField("invalid_time")
	private Date invalidTime;

	public Share() {
	}

	public Share(String shareType, String shareBy,String dataType, String dataId,String shareContent,Date invalidTime) {
		this.shareType = shareType;
		this.shareBy = shareBy;
		this.dataType = dataType;
		this.dataId = dataId;
		this.invalidTime = invalidTime;
		if(ShareTypeEnum.PUBLIC.getCode().equals(shareType))
		{
			//公开分享暂时不设限制
		}
		else if(ShareTypeEnum.PWD.getCode().equals(shareType))
		{
			//密码分享
			this.setSharePwd(shareContent);
		}
		else if(ShareTypeEnum.ORG.getCode().equals(shareType))
		{
			//组织分享
			this.setShareToOrg(shareContent);
		}
		else if(ShareTypeEnum.USER.getCode().equals(shareType))
		{
			//用户分享
			this.setShareToUser(shareContent);
		}
	}

	public Share(String shareType, String shareBy,String dataType, String dataId) {
		this.shareType = shareType;
		this.shareBy = shareBy;
		this.dataType = dataType;
		this.dataId = dataId;
	}



	public String getIsDelete() {
		return isDelete;
	}

	public void setIsDelete(String isDelete) {
		this.isDelete = isDelete;
	}

	public String getShareType() {
		return shareType;
	}

	public void setShareType(String shareType) {
		this.shareType = shareType;
	}

	public String getShareBy() {
		return shareBy;
	}

	public void setShareBy(String shareBy) {
		this.shareBy = shareBy;
	}

	public String getShareToUser() {
		return shareToUser;
	}

	public void setShareToUser(String shareToUser) {
		this.shareToUser = shareToUser;
	}

	public String getDataType() {
		return dataType;
	}

	public void setDataType(String dataType) {
		this.dataType = dataType;
	}

	public String getDataId() {
		return dataId;
	}

	public void setDataId(String dataId) {
		this.dataId = dataId;
	}

	public String getShareToOrg() {
		return shareToOrg;
	}

	public void setShareToOrg(String shareToOrg) {
		this.shareToOrg = shareToOrg;
	}

	public String getSharePwd() {
		return sharePwd;
	}

	public void setSharePwd(String sharePwd) {
		this.sharePwd = sharePwd;
	}

	public Date getInvalidTime() {
		return invalidTime;
	}

	public void setInvalidTime(Date invalidTime) {
		this.invalidTime = invalidTime;
	}
}
