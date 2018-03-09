package com.chens.admin.web.service.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.chens.core.vo.Result;
import com.chens.core.entity.sys.SysUser;
import com.chens.admin.web.mapper.SysUserMapper;
import com.chens.admin.web.service.ISysUserService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author chunlei.song@live.com123
 * @since 2018-03-04
 */
@Service
public class SysUserServiceImpl extends ServiceImpl<SysUserMapper, SysUser> implements ISysUserService {

    @Override
    public Result<SysUser> findByUsername(String username) {
        SysUser query = new SysUser();
        query.setUsername(username);
        List<SysUser> users = this.selectList(new EntityWrapper<>(query));
        if(users!=null && users.size()>0)
        {
            for (SysUser user:users) {
                return ResultHelper.getSuccess(user);
            }
        }
        return ResultHelper.getError(ErorrContants.CANNOT_QUERY_MSG);
    }
}
