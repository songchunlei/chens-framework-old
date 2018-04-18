package com.chens.file.vo.service.impl;

import com.chens.file.vo.AbstractFileQuote;
import com.chens.file.vo.mapper.AbstractFileQuoteMapper;
import com.chens.file.vo.service.IAbstractFileQuoteService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * 
 * 数据-引用文件关系表 服务实现类
 *
 * @author chunlei.song@live.com
 * @create 2018-04-18
 */
@Service
public abstract class AbstractFileQuoteServiceImpl<M extends AbstractFileQuoteMapper<T>, T extends AbstractFileQuote<T>> extends ServiceImpl<M, T> implements IAbstractFileQuoteService<T> {

}
