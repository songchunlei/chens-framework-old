package com.chens.admin.service;

import com.chens.admin.vo.QueryRolesByUserId;
import com.chens.core.entity.SysRole;
import com.baomidou.mybatisplus.service.IService;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author chunlei.song@live.com123
 * @since 2018-03-04
 */
public interface ISysRoleService extends IService<SysRole> {
    /**
     * 根据用户id查询角色
     * @return
     */
    List<SysRole> getRoleListByUserId(String userId);

    /**
     * 保存用户-角色关系
     * @param queryRolesByUserId
     * @return
     */
    boolean saveUserRoleList(QueryRolesByUserId queryRolesByUserId);

    /**
     * 连带用户-角色关系一起删除
     * @param id
     * @return
     */
    boolean deleteWithRel(String id);

}
