package com.chens.admin.mapper;

import com.baomidou.mybatisplus.annotations.SqlParser;
import com.chens.core.entity.SysRole;
import com.baomidou.mybatisplus.mapper.BaseMapper;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author chunlei.song@live.com123
 * @since 2018-03-04
 */
public interface SysRoleMapper extends BaseMapper<SysRole> {

    /**
     * 根据用户id获得角色列表
     * @param userId
     * @return
     */
    List<SysRole> getRoleListByUserId(String userId);
}
