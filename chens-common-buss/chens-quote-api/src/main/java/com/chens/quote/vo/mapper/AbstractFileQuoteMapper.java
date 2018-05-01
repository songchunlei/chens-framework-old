package com.chens.quote.vo.mapper;

import com.chens.quote.vo.AbstractFileQuote;
import com.baomidou.mybatisplus.mapper.BaseMapper;

import java.util.List;

/**
 *
 * 数据-引用文件关系表 Mapper 接口
 *
 * @author chunlei.song@live.com
 * @create 2018-04-18
 */
public interface AbstractFileQuoteMapper<T extends AbstractFileQuote<T>> extends BaseMapper<T> {

    /**
     * 查看文件被哪些数据引用（根据被引入文件的id获取数据列表）
     * @param fileId 被引入文件的id
     * @param fileType 被引入文件类型
     * @param tableName 业务表名
     * @return
     */
    List<String> getDataIdListByFileId(String fileId,String fileType,String tableName);

    /**
     * 查看数据引入了哪些文件（根据数据id获取被引用文件列表）
     * @param dataId 数据id
     * @param dataType 数据类型
     * @param tableName 业务表名
     * @return
     */
    List<String> getFileIdListByDataId(String dataId,String dataType,String tableName);
}