package com.chens.admin.service.impl;

import java.util.Arrays;
import java.util.List;

import com.baomidou.mybatisplus.plugins.Page;
import com.chens.admin.entity.SysUserRole;
import com.chens.admin.exception.AdminExceptionEnum;
import com.chens.admin.service.ISysRoleService;
import com.chens.admin.service.ISysUserRoleService;
import com.chens.admin.vo.RestPwd;
import com.chens.admin.vo.RolesInUserVo;
import com.chens.core.constants.CommonConstants;
import com.chens.core.enums.YesNoEnum;
import com.chens.core.exception.BaseExceptionEnum;
import com.chens.core.util.StringUtils;
import com.chens.core.util.ToolUtil;
import com.chens.core.vo.AuthRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.chens.admin.mapper.SysUserMapper;
import com.chens.admin.service.ISysUserService;
import com.chens.admin.entity.SysUser;
import com.chens.core.exception.BaseException;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

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

    protected Logger logger = LoggerFactory.getLogger(SysUserServiceImpl.class);

    /** spring自带加密工具
     * spring security中的BCryptPasswordEncoder方法采用SHA-256 +随机盐+密钥对密码进行加密。SHA系列是Hash算法，不是加密算法，使用加密算法意味着可以解密（这个与编码/解码一样），但是采用Hash处理，其过程是不可逆的。
     （1）加密(encode)：注册用户时，使用SHA-256+随机盐+密钥把用户输入的密码进行hash处理，得到密码的hash值，然后将其存入数据库中。
     （2）密码匹配(matches)：用户登录时，密码匹配阶段并没有进行密码解密（因为密码经过Hash处理，是不可逆的），而是使用相同的算法把用户输入的密码进行hash处理，得到密码的hash值，然后将其与从数据库中查询到的密码hash值进行比较。如果两者相同，说明用户输入的密码正确
     */
    private BCryptPasswordEncoder encoder = new BCryptPasswordEncoder(CommonConstants.DEFAULT_PASSWORD_LENGTH);


    @Autowired
    private ISysUserRoleService sysUserRoleService;

    /**
     * 重构创建用户方法
     * 增加加密密码算法
     * @param sysUser
     * @return
     */
    @Override
    public boolean insert(SysUser sysUser) {
        //对密码加密
        String password = sysUser.getPassword();
        if (StringUtils.isEmpty(password))
        {
            //设置默认密码
            password = CommonConstants.DEFAULT_PASSWORD;
        }
        sysUser.setPassword(encoder.encode(password));
        sysUser.setIsDelete(YesNoEnum.NO.getCode());
        //创建用户
        super.insert(sysUser);
        //创建角色
        if(StringUtils.isNotEmpty(sysUser.getId()) )
        {
            if(!CollectionUtils.isEmpty(sysUser.getRoles()))
            {
                sysUserRoleService.addRolesInUser(new RolesInUserVo(sysUser.getId(),sysUser.getRoles(),null));
            }
            else
            {
                sysUserRoleService.addRolesInUser(new RolesInUserVo(sysUser.getId(), Arrays.asList(CommonConstants.SYSROLE_COMMON_ROLE),null));
            }
        }

        return true;
    }

    /**
     * 重构用户更新方法
     * 增加保存角色
     * @param sysUser
     * @return
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean updateById(SysUser sysUser) {
        if(sysUser!=null && !CollectionUtils.isEmpty(sysUser.getRoles()))
        {
            //用户id
            String userId = sysUser.getId();
            //1.先清空当前角色
            SysUserRole delete = new SysUserRole();
            delete.setUserId(userId);
            sysUserRoleService.delete(new EntityWrapper<>(delete));
            //2.替换新角色
            sysUserRoleService.addRolesInUser(new RolesInUserVo(userId,sysUser.getRoles(),null));
        }
        if(StringUtils.isNotEmpty(sysUser.getPassword()))
        {
            sysUser.setPassword(encoder.encode(sysUser.getPassword()));
        }
        return super.updateById(sysUser);
    }


    @Override
    public SysUser findByUsername(AuthRequest authRequest) throws BaseException{

        //logger.info("*******SysUserService.findByUsername****************");

        SysUser query = new SysUser();
        query.setUsername(authRequest.getUserName());
        List<SysUser> users = this.selectList(new EntityWrapper<>(query));
        if(users!=null && users.size()>0)
        {
            for (SysUser user:users) {
                if(encoder.matches(authRequest.getPassword(),user.getPassword()))
                {
                    return user;
                }
                else
                {
                    return null;
                }
            }
        }
        return null;
    }

    @Override
    @Transactional
    public String restPwd(RestPwd restPwd) {
        String password = CommonConstants.DEFAULT_PASSWORD;
        if(restPwd.isRandom())
        {
            password = ToolUtil.getRandomString(16);
        }
        SysUser sysUser = new SysUser();
        sysUser.setPassword(password);
        sysUser.setId(restPwd.getUserId());
        if(this.updateById(sysUser))
        {
            return password;
        }
        return null;
    }

    @Override
    public Page<SysUser> getUserListByRoleId(Page<SysUser> page, SysUser user) {
        if(StringUtils.isEmpty(user.getRoleId()))
        {
            throw new BaseException(AdminExceptionEnum.ROLE_ID_IS_NULL);
        }
        page.setRecords(baseMapper.getUserListByRoleId(page,user));
        return page;
    }

    @Override
    public Page<SysUser> getUserListByTenantId(Page<SysUser> page, SysUser user) {
        if(StringUtils.isEmpty(user.getTenantId()))
        {
            logger.error("SysUserServiceImpl==>getUserListByTenantId==>"+AdminExceptionEnum.TENANT_ID_IS_NULL);
            throw new BaseException(AdminExceptionEnum.TENANT_ID_IS_NULL);
        }
        SysUser query = new SysUser();
        query.setTenantId(user.getTenantId());
        return this.selectPage(page,new EntityWrapper<>(query));
    }

}
