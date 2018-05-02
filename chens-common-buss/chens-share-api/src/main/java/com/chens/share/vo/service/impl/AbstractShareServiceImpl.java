package com.chens.share.vo.service.impl;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.chens.coder.IQRCode;
import com.chens.coder.QRConfig;
import com.chens.core.exception.FileException;
import com.chens.core.exception.FileExceptionEnum;
import com.chens.share.vo.AbstractShare;
import com.chens.share.vo.mapper.AbstractShareMapper;
import com.chens.share.vo.service.IAbstractShareService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;

/**
 * 
 * 站内分享 服务实现类
 *
 * @author chunlei.song@live.com
 * @create 2018-04-29
 */
@Service
public abstract class AbstractShareServiceImpl<M extends AbstractShareMapper<T>, T extends AbstractShare<T>> extends ServiceImpl<M, T> implements IAbstractShareService<T> {

    @Autowired
    private IQRCode qrCode;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public T insertAndCreateQRCoder(T t,String url,String savePath)
    {
        //增加二维码生成(使用默认配置)
        QRConfig qrConfig =  new QRConfig(url, 400, 400, null, null);
        try {
            t.setUrl(qrCode.create(qrConfig,savePath));
        } catch (IOException e) {
            throw new FileException(FileExceptionEnum.FILE_SAVE_ERROR.getCode(),e.getMessage());
        }
        //先保存业务，如果二维码生成异常则回滚
        super.insert(t);
        return t;
    }



}
