package com.chens.quote.vo.service;

import com.chens.quote.vo.AbstractFileQuote;
import com.baomidou.mybatisplus.service.IService;

import java.util.List;

/**
 *
 * 数据-引用文件关系表 服务接口
 *
 * @author chunlei.song@live.com
 * @create 2018-04-18
 */
public interface IAbstractFileQuoteService<T extends AbstractFileQuote<T>> extends IService<T> {

    /**
     * 查看文件被哪些数据引用（根据被引入文件的id获取数据列表）
     * @param abstractFileQuote
     * @return
     */
    List<String> getDataIdListByQuoteFileId(AbstractFileQuote<T>  abstractFileQuote);

    /**
     * 查看数据引入了哪些文件（根据数据id获取被引用文件列表）
     * @param abstractFileQuote
     * @return
     */
    List<String> getQuoteFileIdListByDataId(AbstractFileQuote<T>  abstractFileQuote);

}
