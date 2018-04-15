package com.chens.file.vo.service.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.service.IService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.chens.core.constants.CommonConstants;
import com.chens.core.exception.BaseException;
import com.chens.core.exception.BaseExceptionEnum;
import com.chens.core.util.FolderUtil;
import com.chens.core.util.StringUtils;
import com.chens.core.vo.FolderFileInfo;
import com.chens.file.constants.FileConstants;
import com.chens.file.exception.FileExceptionEnum;
import com.chens.file.vo.AbstractFolder;
import com.chens.file.vo.mapper.AbstractFolderMapper;
import com.chens.file.vo.service.IAbstractFolderService;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * 文件夹表 服务实现类
 * </p>
 *
 * @author wdp123
 * @since 2018-03-06
 */
public abstract class AbstractFolderServiceImpl<M extends AbstractFolderMapper<T>, T extends AbstractFolder<T>>  extends ServiceImpl<M, T> implements IAbstractFolderService<T> {

    /**
     * 根据文件夹id获取文件信息
     * @param folderId
     * @return
     */
    protected abstract List<FolderFileInfo>  getFileInfoListByFolderId(String folderId);

    @Override
    public FolderFileInfo selectFolderById(String id) {

        FolderFileInfo forderFileInfo;

        //1. 寻找父文件夹
        if(CommonConstants.BASE_TREE_ROOT.equals(id))
        {
            //当id为根目录，初始化
            forderFileInfo = FolderUtil.getRootForder(CommonConstants.BASE_TREE_ROOT_NAME);
        }
        else
        {
            T t = this.selectById(id);
            if(t==null)
            {
                throw new BaseException(BaseExceptionEnum.NO_DATA);
            }
            //获取当前文件夹信息
            forderFileInfo = t.getForderFileInfo();
            //当前文件夹是父文件夹时，不往上找一层
            T parent = this.selectById(t.getParentId());
            if (parent != null)
            {
                forderFileInfo.setParent(parent.getForderFileInfo());
            }
        }

        //2. 获取当前文件夹下的文件夹
        EntityWrapper<T> wrapper = new EntityWrapper<T>();
        wrapper.eq(FileConstants.FOLDER_COLUMN_PARENT_ID,id);
        List<T> childForderTemps = this.selectList(wrapper);
        List<FolderFileInfo> childForders = new ArrayList<>();
        for (T temp:childForderTemps)
        {
            childForders.add(temp.getForderFileInfo());
        }
        if(childForders!=null && childForders.size()>0)
        {
            forderFileInfo.setChildren(childForders);
        }


        //3. 获取当前文件夹下的文件,各服务自己定义
        forderFileInfo.setFiles(getFileInfoListByFolderId(id));

        //4.返回
        return forderFileInfo;
    }

    @Override
    public boolean insertFolder(T entity)
    {
        int lvl = 2;
        if(entity==null)
        {
            throw new BaseException(BaseExceptionEnum.REQUEST_NULL);
        }
        String parentId = entity.getParentId();

        if(StringUtils.isNotEmpty(parentId))
        {
            if(CommonConstants.BASE_TREE_ROOT.equals(parentId))
            {
                lvl = 2;
            }
            else
            {
                T parentFolder = this.selectById(parentId);
                lvl = Integer.sum(parentFolder.getLvl(),1);
            }
            entity.setLvl(lvl);
            return insert(entity);
        }
        else
        {
            throw new BaseException(FileExceptionEnum.FOLDER_PARENT_ID_IS_NULL);
        }
    }

}
