package com.chens.admin.web.controller;



import com.chens.admin.service.ISysUserService;
import com.chens.core.entity.SysUser;
import com.chens.core.exception.BaseException;
import com.chens.core.exception.BaseExceptionEnum;
import com.chens.core.vo.Result;
import com.chens.core.web.BaseWebController;
import feign.Param;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestParam;

import javax.validation.constraints.NotNull;


/**
 * <p>
 *  用户管理
 * </p>
 *
 * @author chunlei.song@live.com123
 * @since 2018-03-04
 */
@Controller
@RequestMapping("/userController")
public class SysUserController extends BaseWebController<ISysUserService,SysUser> {

    /**
     * 重置密码
     * @param userId 用户id
     * @param isRandom 是否随机
     * @return
     */
    @PostMapping("/restPwd")
    public ResponseEntity<Result> restPwd(@RequestParam("userId") Long userId, @RequestParam("isRandom") boolean isRandom) {
        return doSuccess(service.restPwd(userId,isRandom));
    }

    /**
     * 根据角色id获取角色下的用户
     * @param roleId
     * @return
     */
    @GetMapping("/getUserListByRoleId")
    public ResponseEntity<Result> getUserListByRoleId(@NotNull Long roleId) {
        if(roleId!=null){
            return doSuccess(service.getUserListByRoleId(roleId));
        } else {
            throw new BaseException(BaseExceptionEnum.REQUEST_NULL);
        }
    }
}

