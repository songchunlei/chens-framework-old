package com.chens.admin.mapper;


import com.baomidou.mybatisplus.annotations.SqlParser;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.chens.core.entity.SysPermission;
import com.chens.core.vo.ZTree;

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
public interface SysPermissionMapper extends BaseMapper<SysPermission> {

    /**
     * 返回树形
     * @return
     */
    List<ZTree> tree();
}
