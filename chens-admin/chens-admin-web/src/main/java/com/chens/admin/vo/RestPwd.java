package com.chens.admin.vo;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * 重置密码
 *
 * @author songchunlei@qq.com
 * @create 2018/4/18
 */
public class RestPwd implements Serializable{

    /**
     * 用户ID
     */
    @NotNull(message = "{sysuser.id.null}")
    String userId;

    /**
     * 是否随机
     */
    boolean random;

    public RestPwd() {

    }

    public RestPwd(String userId, boolean random) {
        this.userId = userId;
        this.random = random;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public boolean isRandom() {
        return random;
    }

    public void setRandom(boolean random) {
        this.random = random;
    }
}
