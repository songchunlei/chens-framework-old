package com.chens.admin.web.mapper;

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
    List<SysRole> findRoleListByUserId(Long userId);
}
