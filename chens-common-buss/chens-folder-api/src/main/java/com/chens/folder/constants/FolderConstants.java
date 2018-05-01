package com.chens.folder.constants;

/**
 * 文件夹
 *
 * @author songchunlei@qq.com
 * @create 2018/5/1
 */
public class FolderConstants {
    /**
     * 最大文件夹数量
     */
    public static final int MAX_FOLDER_COUNT = 9999;

    /**
     * 文件夹语意占位
     */
    public static final String FOLD_CASCADE_FORMAT = "0000";

    /**
     * 文件夹语意区隔符号
     */
    public static final String FOLD_CASCADE_FORMAT_REGEX = ".";



    /**
     * 父文件夹目录字段
     */
    public static final String FOLDER_COLUMN_PARENT_ID = "parent_id";

    /**
     * 文件夹类型字段
     */
    public static final String FOLDER_COLUMN_TYPE = "type";

    /**
     * 文件夹目录字段
     */
    public static final String FOLDER_FILE_COLUMN_FOLDER_ID = "folder_id";

    /**
     * 文件夹语意id字段
     */
    public static final String FOLDER_FILE_COLUMN_CASCADE_ID = "cascade_id";

    /**
     * 文件夹等级字段
     */
    public static final String FOLDER_FILE_COLUMN_LVL = "lvl";
}
