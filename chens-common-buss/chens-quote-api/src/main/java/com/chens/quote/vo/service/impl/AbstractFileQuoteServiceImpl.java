package com.chens.quote.vo.service.impl;

import com.baomidou.mybatisplus.annotations.TableName;
import com.chens.core.exception.BaseException;
import com.chens.core.exception.BaseExceptionEnum;
import com.chens.core.util.StringUtils;
import com.chens.quote.vo.AbstractFileQuote;
import com.chens.quote.vo.exception.QuoteExceptionEnum;
import com.chens.quote.vo.mapper.AbstractFileQuoteMapper;
import com.chens.quote.vo.service.IAbstractFileQuoteService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 
 * 数据-引用文件关系表 服务实现类
 *
 * @author chunlei.song@live.com
 * @create 2018-04-18
 */
@Service
public abstract class AbstractFileQuoteServiceImpl<M extends AbstractFileQuoteMapper<T>, T extends AbstractFileQuote<T>> extends ServiceImpl<M, T> implements IAbstractFileQuoteService<T> {


    @Override
    public List<String> getDataIdListByQuoteFileId(AbstractFileQuote<T>  abstractFileQuote) {
        TableName tableName = abstractFileQuote.getClass().getAnnotation(TableName.class);
        if(tableName==null)
        {
            throw new BaseException(BaseExceptionEnum.NO_TABLE_NAME);
        }
        if(StringUtils.isEmpty(abstractFileQuote.getFileId()))
        {
            throw new BaseException(QuoteExceptionEnum.FILE_ID_IS_NULL);
        }
        if(StringUtils.isEmpty(abstractFileQuote.getFileType()))
        {
            throw new BaseException(QuoteExceptionEnum.FILE_TYPE_IS_NULL);
        }
        return baseMapper.getDataIdListByFileId(abstractFileQuote.getFileId(),abstractFileQuote.getFileType(),tableName.value());
    }

    @Override
    public List<String> getQuoteFileIdListByDataId(AbstractFileQuote<T>  abstractFileQuote) {
        TableName tableName = abstractFileQuote.getClass().getAnnotation(TableName.class);
        if(tableName==null)
        {
            throw new BaseException(BaseExceptionEnum.NO_TABLE_NAME);
        }
        if(StringUtils.isEmpty(abstractFileQuote.getDataId()))
        {
            throw new BaseException(QuoteExceptionEnum.DATA_ID_IS_NULL);
        }
        if(StringUtils.isEmpty(abstractFileQuote.getType()))
        {
            throw new BaseException(QuoteExceptionEnum.DATA_TYPE_IS_NULL);
        }
        return baseMapper.getDataIdListByFileId(abstractFileQuote.getDataId(),abstractFileQuote.getType(),tableName.value());
    }
}
