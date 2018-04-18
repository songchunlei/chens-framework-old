package com.chens.file.vo.controller;

import com.chens.file.vo.service.IAbstractFileQuoteService;
import com.chens.file.vo.AbstractFileQuote;


import com.chens.core.web.BaseWebController;

/**
 *
 * 数据-引用文件抽象
 *
 * @author chunlei.song@live.com
 * @create 2018-04-18
 */
public abstract class AbstractFileQuoteController<S extends IAbstractFileQuoteService<T>, T extends AbstractFileQuote<T>> extends BaseWebController<S,T> {


}
