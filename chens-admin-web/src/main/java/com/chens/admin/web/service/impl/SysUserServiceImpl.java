package com.chens.admin.web.service.impl;

import java.util.List;

import com.chens.core.util.StringUtils;
import com.chens.core.vo.AuthRequest;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.chens.admin.web.mapper.SysUserMapper;
import com.chens.admin.web.service.ISysUserService;
import com.chens.core.entity.SysUser;
import com.chens.core.exception.BaseException;
import com.chens.core.exception.BaseExceptionEnum;

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
    public SysUser findByUsername(AuthRequest authRequest){
        if(authRequest==null)
        {
            throw new BaseException(BaseExceptionEnum.REQUEST_NULL);
        }

        if(StringUtils.isEmpty(authRequest.getUserName()))
        {
            throw new BaseException(BaseExceptionEnum.AUTH_REQUEST_NO_USERNAME);
        }

        if(StringUtils.isEmpty(authRequest.getPassword()))
        {
            throw new BaseException(BaseExceptionEnum.AUTH_REQUEST_NO_PASSWORD);
        }
        SysUser query = new SysUser();
        query.setUsername(authRequest.getUserName());
        query.setPassword(authRequest.getPassword());
        List<SysUser> users = this.selectList(new EntityWrapper<>(query));
        if(users!=null && users.size()>0)
        {
            for (SysUser user:users) {
                return user;
            }
        }
        throw new BaseException(BaseExceptionEnum.NO_DATA);
    }
}
