package com.chens.folder.util;

import com.chens.core.constants.CommonConstants;
import com.chens.core.enums.FileTypeEnum;
import com.chens.folder.vo.FolderFileInfo;

import java.util.Date;

/**
 * 文件夹工具
 *
 * @author songchunlei@qq.com
 * @create 2018/4/11
 */
public final class FolderUtil {
    public static FolderFileInfo getInstance() {
        return new FolderFileInfo();
    }

    public static FolderFileInfo getRootForder(String name) {
        return new FolderFileInfo(CommonConstants.BASE_TREE_ROOT,null, FileTypeEnum.FOLDER.getCode(), 1,name,new Date());
    }
}
