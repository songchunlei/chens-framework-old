package com.chens.file.service;

import com.chens.file.entity.SysFile;
import com.baomidou.mybatisplus.service.IService;

import java.util.List;

/**
 * <p>
 *  文件信息查询
 * </p>
 *
 * @author chunlei.song@live.com123
 * @since 2018-03-09
 */
public interface IFileInfoService extends IService<SysFile> {

    /**
     * 判断md5对应的文件是否存在
     * @param fileMd5
     * @return
     */
    boolean isMd5Exist(String fileMd5);

    /**
     * 根据groupId查询
     * @param groupId
     * @return
     */
    List<SysFile> getFilesByGroupId(String groupId);

    /**
     * 根据文件名找文件信息
     * @param name
     * @return
     */
    SysFile loadByName(String name);
}
