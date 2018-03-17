package com.chens.admin.service.impl;

import com.chens.core.entity.SysMenu;
import com.chens.admin.mapper.SysMenuMapper;
import com.chens.admin.service.ISysMenuService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.chens.core.vo.ZTree;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author chunlei.song@live.com123
 * @since 2018-03-09
 */
@Service
public class SysMenuServiceImpl extends ServiceImpl<SysMenuMapper, SysMenu> implements ISysMenuService {

    @Override
    public List<ZTree> tree() {
        return baseMapper.tree();
    }
}
