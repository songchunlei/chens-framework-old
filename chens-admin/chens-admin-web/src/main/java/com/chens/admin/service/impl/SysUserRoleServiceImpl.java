package com.chens.admin.service.impl;

import com.chens.core.entity.SysUserRole;
import com.chens.admin.mapper.SysUserRoleMapper;
import com.chens.admin.service.ISysUserRoleService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.chens.core.util.StringUtils;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * 
 *  服务实现类
 *
 * @author chunlei.song@live.com
 * @create 2018-03-17
 */
@Service
public class SysUserRoleServiceImpl extends ServiceImpl<SysUserRoleMapper, SysUserRole> implements ISysUserRoleService {

    private final String DEFAULT_USERS_SPLIT_FLG=",";

    @Override
    public boolean AddUsersInRole(Long roleId, String users) {
        boolean  flagUserRole = false;
        if (StringUtils.isNotEmpty(users)) {
            List<SysUserRole> userRoleList = new ArrayList<>();
            String[] checkeds = users.split(DEFAULT_USERS_SPLIT_FLG);
            for (String s : checkeds) {
                SysUserRole r = new SysUserRole(Long.valueOf(s),roleId);
                userRoleList.add(r);
            }
            // 将选中角色id进行保存处理
            flagUserRole = this.insertBatch(userRoleList);
        }
        return flagUserRole;
    }
}
