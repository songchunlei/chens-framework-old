package com.chens.file.vo.service.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.chens.core.constants.CommonConstants;
import com.chens.core.exception.BaseException;
import com.chens.core.exception.BaseExceptionEnum;
import com.chens.file.exception.FileException;
import com.chens.file.util.FolderUtil;
import com.chens.core.util.StringUtils;
import com.chens.file.vo.FolderFileInfo;
import com.chens.file.constants.FileConstants;
import com.chens.file.exception.FileExceptionEnum;
import com.chens.file.vo.AbstractFolder;
import com.chens.file.vo.mapper.AbstractFolderMapper;
import com.chens.file.vo.service.IAbstractFolderService;
import org.springframework.util.CollectionUtils;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

import static java.lang.System.currentTimeMillis;

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
     * 自定义文件类型
     * @return
     */
    protected abstract String initType();

    /**
     * 根据文件夹id获取文件信息
     * @param folderId
     * @return
     */
    protected abstract List<FolderFileInfo>  getFileInfoListByFolderId(String folderId);

    @Override
    public FolderFileInfo selectFolderById(String id) {

        //当前文件夹
        FolderFileInfo folderFileInfo;
        //根文件夹
        FolderFileInfo rootFolder = FolderUtil.getRootForder(CommonConstants.BASE_TREE_ROOT_NAME);

        //路径
        List<FolderFileInfo> trees = new ArrayList<>();
        trees.add(rootFolder);


        //1. 寻找父文件夹
        if(CommonConstants.BASE_TREE_ROOT.equals(id))
        {
            //当id为根目录，初始化
            folderFileInfo = rootFolder;
            //放入路径
            trees.add(folderFileInfo);
        }
        else
        {
            T t = this.selectById(id);
            if(t==null)
            {
                throw new BaseException(BaseExceptionEnum.NO_DATA);
            }

            //获取当前文件夹信息
            folderFileInfo = t.getFolderFileInfo();
            //当前文件夹是父文件夹时，不往上找一层
            T parent = this.selectById(t.getParentId());
            if (parent != null)
            {
                folderFileInfo.setParent(parent.getFolderFileInfo());
                //当lvl大于2查找路径，当等于2父路径就是上一个路径
                if(parent.getLvl()>2)
                {
                    //父节点语意id，格式为 .0000.0000.0000
                    String parentCascadeId = parent.getCascadeId();
                    List<String> cascadeIdList =  StringUtils.string2ListSpc(parentCascadeId,FileConstants.FOLD_CASCADE_FORMAT_REGEX);
                    if(!CollectionUtils.isEmpty(cascadeIdList))
                    {
                        //获取路过的文件夹
                        EntityWrapper<T> wrapper = new EntityWrapper<>();
                        wrapper.eq(FileConstants.FOLDER_COLUMN_TYPE,initType());
                        wrapper.andNew();
                        for (String cascadeIdTemp:cascadeIdList)
                        {
                            if(StringUtils.isNotEmpty(cascadeIdTemp))
                            {
                                wrapper.or(FileConstants.FOLDER_FILE_COLUMN_CASCADE_ID,cascadeIdTemp);
                            }
                        }
                        List<T> allParentFolder = this.selectList(wrapper);
                        if(!CollectionUtils.isEmpty(cascadeIdList))
                        {
                            for (T temp :allParentFolder) {
                                trees.add(temp.getFolderFileInfo());
                            }
                        }
                    }
                }
                else
                {
                    trees.add(parent.getFolderFileInfo());
                }
            }
            //2.放入路径
            folderFileInfo.setTree(trees);
        }

        //3. 获取当前文件夹下的文件夹
        EntityWrapper<T> wrapper = new EntityWrapper<>();
        wrapper.eq(FileConstants.FOLDER_COLUMN_PARENT_ID,id);
        wrapper.eq(FileConstants.FOLDER_COLUMN_TYPE,initType());
        List<T> childFolderTemps = this.selectList(wrapper);
        List<FolderFileInfo> childFolders = new ArrayList<>();
        for (T temp:childFolderTemps)
        {
            childFolders.add(temp.getFolderFileInfo());
        }


        //4. 获取当前文件夹下的文件,各服务自己定义
        List<FolderFileInfo> files = getFileInfoListByFolderId(id);
        if(!CollectionUtils.isEmpty(files))
        {
            childFolders.addAll(files);
        }

        //4.放入文件/文件夹
        folderFileInfo.setChildren(childFolders);

        //5.返回
        return folderFileInfo;
    }

    @Override
    public boolean insertFolder(T entity)
    {
        //一个目录下最大9999
        DecimalFormat df=new DecimalFormat(FileConstants.FOLD_CASCADE_FORMAT);

        //定义当前等级
        int lvl = 2;
        String parentCascadeId = "";

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
                parentCascadeId = parentFolder.getCascadeId();
            }

            //2.获取当前文件夹下的语意id最大值（数量+1）
            EntityWrapper<T> wrapper = new EntityWrapper<>();
            wrapper.eq(FileConstants.FOLDER_COLUMN_PARENT_ID,parentId);
            wrapper.eq(FileConstants.FOLDER_COLUMN_TYPE,initType());
            int count = this.selectCount(wrapper);
            if(count+1>FileConstants.MAX_FOLDER_COUNT)
            {
                throw new FileException(FileExceptionEnum.FILE_MAX);
            }
            //3.插入语意id
            entity.setCascadeId(parentCascadeId+"."+df.format(count+1));
            //4.设置lvl
            entity.setLvl(lvl);
            return this.insert(entity);
        }
        else
        {
            throw new BaseException(FileExceptionEnum.FOLDER_PARENT_ID_IS_NULL);
        }
    }





}
