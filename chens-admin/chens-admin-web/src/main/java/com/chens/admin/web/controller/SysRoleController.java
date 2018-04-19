package com.chens.admin.web.controller;


import com.chens.admin.service.ISysRoleService;
import com.chens.admin.service.ISysUserRoleService;
import com.chens.admin.entity.SysRole;
import com.chens.admin.vo.UsersInRoleVo;
import com.chens.admin.vo.RolesInUserVo;
import com.chens.core.constants.CommonConstants;
import com.chens.core.exception.BaseException;
import com.chens.core.exception.BaseExceptionEnum;
import com.chens.core.vo.Result;
import com.chens.core.web.BaseWebController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import org.springframework.stereotype.Controller;

/**
 * <p>
 *  角色管理
 * </p>
 *
 * @author chunlei.song@live.com123
 * @since 2018-03-04
 */
@Controller
@RequestMapping("/roleController")
public class SysRoleController extends BaseWebController<ISysRoleService,SysRole> {

    @Autowired
    private ISysUserRoleService sysUserRoleService;

    /**
     * 连带用户-角色关系一起删除
     * @param id
     * @return
     */
    @DeleteMapping("/deleteWithRel/{id}")
    public ResponseEntity<Result> deleteWithRel(@PathVariable String id) {
        if(id!=null)
        {
            return doSuccess(CommonConstants.DELETE_SUCCESS,service.deleteWithRel(id));
        }
        else{
            throw new BaseException(BaseExceptionEnum.REQUEST_NULL);
        }
    }

    /**
     * 获取根据用户id角色列表
     * @param userId
     * @return
     */
    @GetMapping("/getRoleListByUserId")
    public ResponseEntity<Result> getRoleListByUserId(String userId) {
        if(userId!=null){
            return doSuccess(CommonConstants.QUERY_SUCCESS,service.getRoleListByUserId(userId));
        } else {
            throw new BaseException(BaseExceptionEnum.REQUEST_NULL);
        }
    }

    /**
     * 保存用户-角色关系
     * @param queryRolesByUserId
     * @return
     */
    @PostMapping("/saveUserRoleList")
    public ResponseEntity<Result> saveUserRoleList(RolesInUserVo queryRolesByUserId) {
        if(queryRolesByUserId !=null){
            return doSuccess(CommonConstants.SAVE_SUCCESS,sysUserRoleService.addRolesInUser(queryRolesByUserId));
        } else {
            throw new BaseException(BaseExceptionEnum.REQUEST_NULL);
        }
    }

    /**
     * 增加用户
     * @param usersInRoleVo
     * @return
     */
    @PostMapping("/addUsers")
    public ResponseEntity<Result> addUsers(UsersInRoleVo usersInRoleVo) {
        return doSuccess(CommonConstants.SAVE_SUCCESS,sysUserRoleService.addUsersInRole(usersInRoleVo));
    }

    /**
     * 删除用户
     * @param usersInRoleVo
     * @return
     */
    @DeleteMapping("/deleteUsers")
    public ResponseEntity<Result> deleteUsers(UsersInRoleVo usersInRoleVo) {
        return doSuccess(CommonConstants.DELETE_SUCCESS,sysUserRoleService.deleteUsersInRole(usersInRoleVo));
    }

}

