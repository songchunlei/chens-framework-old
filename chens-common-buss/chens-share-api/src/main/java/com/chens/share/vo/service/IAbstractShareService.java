package com.chens.share.vo.service;

import com.baomidou.mybatisplus.service.IService;
import com.chens.share.vo.AbstractShare;

/**
 *
 * 站内分享 服务接口
 *
 * @author chunlei.song@live.com
 * @create 2018-04-29
 */
public interface IAbstractShareService<T extends AbstractShare> extends IService<T> {

    /**
     * 新建并创建二维码
     * @param t 分享实体
     * @param url 分享url
     * @param savePath 二维码保存位置
     * @return
     */
    T insertAndCreateQRCoder(T t,String url,String savePath);
}
