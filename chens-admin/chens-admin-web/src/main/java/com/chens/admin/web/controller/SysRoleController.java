package com.chens.admin.web.controller;


import com.chens.admin.service.ISysRoleService;
import com.chens.admin.service.ISysUserRoleService;
import com.chens.core.entity.SysRole;
import com.chens.core.exception.BaseException;
import com.chens.core.exception.BaseExceptionEnum;
import com.chens.core.vo.Result;
import com.chens.admin.vo.QueryRolesByUserId;
import com.chens.core.web.BaseWebController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import org.springframework.stereotype.Controller;

import javax.validation.constraints.NotNull;

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
            return doSuccess("删除成功",service.deleteWithRel(id));
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
            return doSuccess(service.getRoleListByUserId(userId));
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
    public ResponseEntity<Result> saveUserRoleList(QueryRolesByUserId queryRolesByUserId) {
        if(queryRolesByUserId !=null){
            return doSuccess(service.saveUserRoleList(queryRolesByUserId));
        } else {
            throw new BaseException(BaseExceptionEnum.REQUEST_NULL);
        }
    }

    /**
     * 增加用户
     * @param roleId
     * @param users
     * @return
     */
    @PostMapping("/addUsers")
    public ResponseEntity<Result> addUsers(@NotNull(message = "{role.id.null}") String roleId,@NotNull(message = "{role.users.null}") String userIds) {
        return doSuccess(sysUserRoleService.AddUsersInRole(roleId,userIds));
    }

    /**
     * 删除用户
     * @param roleId
     * @param users
     * @return
     */
    @DeleteMapping("/deleteUsers")
    public ResponseEntity<Result> deleteUsers(@NotNull(message = "{role.id.null}") String roleId,@NotNull(message = "{role.users.null}") String userIds) {
        return doSuccess(sysUserRoleService.DeleteUsersInRole(roleId,userIds));
    }

}

