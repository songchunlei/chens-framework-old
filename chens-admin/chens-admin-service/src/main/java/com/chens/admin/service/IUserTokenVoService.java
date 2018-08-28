package com.chens.admin.service;

import com.chens.admin.vo.UserTokenVo;
import com.chens.core.vo.UserInfo;

/**
 * 获取用户全量数据服务
 *
 * @author songchunlei@qq.com
 * @create 2018/8/28
 */
public interface IUserTokenVoService {

    /**
     * 根据UserInfo获取UserTokenVo
     *
     * @param userInfo
     * @return
     */
    UserTokenVo getUserTokenVo(UserInfo userInfo);
}
