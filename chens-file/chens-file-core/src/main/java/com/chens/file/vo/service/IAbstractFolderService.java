package com.chens.file.vo.service;

import com.baomidou.mybatisplus.service.IService;
import com.chens.file.vo.FolderFileInfo;
import com.chens.file.vo.AbstractFolder;


/**
 * <p>
 * 文件夹表 服务类
 * </p>
 *
 * @author wdp123
 * @since 2018-03-06
 */
public interface IAbstractFolderService<T extends AbstractFolder<T>> extends IService<T> {

    /**
     * 根据id查询文件夹以及文件信息
     * @param id
     * @return
     */
    FolderFileInfo selectFolderById(String id);

    /**
     * 创建文件夹
     * @param entity
     * @return
     */
    boolean insertFolder(T entity);
}
