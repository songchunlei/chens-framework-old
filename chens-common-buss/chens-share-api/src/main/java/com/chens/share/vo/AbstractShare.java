package com.chens.share.vo;

import java.util.Date;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableLogic;
import com.chens.core.enums.YesNoEnum;
import com.chens.core.vo.BaseEntity;
import com.chens.share.constants.ShareConstants;
import com.chens.share.enums.ShareTypeEnum;

import javax.validation.constraints.NotNull;

/**
 *
 * 站内分享 实体
 * @TableName("t_share")
 * @author chunlei.song@live.com
 * @create 2018-04-29
 */
public abstract class AbstractShare<T extends AbstractShare> extends BaseEntity<T> {

    private static final long serialVersionUID = 1L;

	@TableField("is_delete")
	@TableLogic
	protected String isDelete = YesNoEnum.NO.getCode();
    /**
     * 分享类型，组织分享，用户分享,密码分享
     */
    @NotNull
	@TableField("share_type")
	protected String shareType;
    /**
     * 分享人id
     */
	@NotNull
	@TableField("share_by")
	protected String shareBy;
    /**
     * 被分享人id
     */
	@TableField("share_to_user")
	protected String shareToUser;

	/**
	 * 数据类型，自定义
	 */
	@TableField("data_type")
	protected String dataType;

    /**
     * 数据id
     */
    @NotNull
	@TableField(ShareConstants.SHARE_COLUMN_DATA_ID)
	protected String dataId;
    /**
     * 被分享组织id
     */
	@TableField("share_to_org")
	protected String shareToOrg;
    /**
     * 密码分享
     */
	@TableField("share_pwd")
	protected String sharePwd;

	/**
	 * 失效时间
	 */
	@TableField("invalid_time")
	protected Date invalidTime;

	/**
	 * 二维码地址
	 */
	protected String url;

	public AbstractShare() {

	}

	public AbstractShare(String shareType, String shareBy, String dataType, String dataId, String shareContent, Date invalidTime) {
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

	public AbstractShare(String shareType, String shareBy, String dataType, String dataId) {
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

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

}
