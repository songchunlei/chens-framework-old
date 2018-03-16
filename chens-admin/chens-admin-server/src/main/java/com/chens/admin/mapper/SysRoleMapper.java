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
//取消租户限制
@SqlParser(filter = true)
public interface SysRoleMapper extends BaseMapper<SysRole> {
    List<SysRole> findRoleListByUserId(Long userId);

    List<SysRole> findRoleListByPermissionId(Long userId);
}
