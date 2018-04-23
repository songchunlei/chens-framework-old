package com.chens.file.constants;

/**
 * 常量
 *
 * @author songchunlei@qq.com
 * @create 2018/4/3
 */
public class FileConstants {

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
     * 上传成功
     */
    public static final String UPLOAD_SUCCESS="上传成功";
    public static final String DELETE_SUCCESS="文件删除成功";
    public static final String UPLOAD_ERROR="上传失败";
    public static final String DOWNLOAD_ERROR="下载失败";



}
