package com.chens.file.service.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.chens.file.entity.SysFile;
import com.chens.file.mapper.SysFileMapper;
import com.chens.file.service.IFileInfoService;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 *  文件信息查询服务实现类
 * </p>
 *
 * @author chunlei.song@live.com123
 * @since 2018-03-09
 */
@Service
public class SysFileServiceImpl extends ServiceImpl<SysFileMapper, SysFile> implements IFileInfoService {

    @Override
    public boolean isMd5Exist(String fileMd5) {

        SysFile sysFile = new SysFile();
        sysFile.setMd5(fileMd5);
        EntityWrapper<SysFile> sysFileEntityWrapper =  new EntityWrapper<>(sysFile);

        int count = this.selectCount(sysFileEntityWrapper);
        if(count>0)
        {
            return true;
        }
        return false ;
    }

    @Override
    public List<SysFile> getFilesByGroupId(String groupId) {
        return null;
    }
}
