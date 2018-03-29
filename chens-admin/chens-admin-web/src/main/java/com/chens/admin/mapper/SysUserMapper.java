package com.chens.admin.mapper;

import com.baomidou.mybatisplus.plugins.Page;
import com.chens.admin.entity.SysUser;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author chunlei.song@live.com123
 * @since 2018-03-04
 */
public interface SysUserMapper extends BaseMapper<SysUser> {


    /**
     * 根据角色id，用户名称查询用户列表
     * @param page
     * @param user
     * @return
     */
    List<SysUser> getUserListByRoleId(@Param("page") Page<SysUser> page,@Param("user") SysUser user);
}
