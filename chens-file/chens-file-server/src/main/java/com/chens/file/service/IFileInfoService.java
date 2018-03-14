package com.chens.file.service;

import com.chens.file.entity.SysFile;
import com.baomidou.mybatisplus.service.IService;

/**
 * <p>
 *  文件信息查询
 * </p>
 *
 * @author chunlei.song@live.com123
 * @since 2018-03-09
 */
public interface IFileInfoService extends IService<SysFile> {

    boolean isMd5Exist(String fileMd5);
}
