package com.chens.core.util;

import com.chens.core.constants.CommonConstants;
import com.chens.core.enums.FileType;
import com.chens.core.vo.FolderFileInfo;

import java.util.Date;

/**
 * 文件夹工具
 *
 * @auther songchunlei@qq.com
 * @create 2018/4/11
 */
public final class FolderUtil {
    public static FolderFileInfo getInstance() {
        return new FolderFileInfo();
    }

    public static FolderFileInfo getRootForder(String name) {
        return new FolderFileInfo(CommonConstants.BASE_TREE_ROOT, FileType.FOLDER.getCode(), name,new Date());
    }
}
