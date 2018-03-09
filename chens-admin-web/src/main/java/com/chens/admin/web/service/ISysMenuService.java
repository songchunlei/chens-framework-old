package com.chens.admin.web.service;

import com.chens.core.entity.SysMenu;
import com.baomidou.mybatisplus.service.IService;
import com.chens.core.vo.ZTree;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author chunlei.song@live.com123
 * @since 2018-03-09
 */
public interface ISysMenuService extends IService<SysMenu> {

    List<ZTree> tree();
}
