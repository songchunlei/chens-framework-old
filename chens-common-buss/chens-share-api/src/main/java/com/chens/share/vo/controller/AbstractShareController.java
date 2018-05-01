package com.chens.share.vo.controller;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.chens.core.annotation.InsertValid;
import com.chens.core.constants.CommonConstants;
import com.chens.core.context.BaseContextHandler;
import com.chens.core.exception.BaseException;
import com.chens.core.exception.BaseExceptionEnum;
import com.chens.core.util.UUIDUtil;
import com.chens.core.vo.Result;
import com.chens.share.constants.ShareConstants;
import com.chens.share.exception.ShareExceptionEnum;
import org.apache.commons.lang.StringUtils;
import org.springframework.http.ResponseEntity;
import org.springframework.util.CollectionUtils;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import com.chens.share.vo.service.IAbstractShareService;
import com.chens.share.vo.AbstractShare;


import com.chens.core.web.BaseWebController;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.Date;
import java.util.List;

/**
 *
 * 站内分享 控制器
 *
 * @author chunlei.song@live.com
 * @create 2018-04-29
 */
public abstract class AbstractShareController<S extends IAbstractShareService<T>, T extends AbstractShare<T>> extends BaseWebController<S,T> {

    /**
     * 自定义分享网址，用于分享拼接id
     * 如：  api.songchenyu.com/shareController/id=,则拼接成api.songchenyu.com/shareController?id=xxxx
     * @return
     */
    protected abstract String getRootURL();

    /**
     * 自定义二维码保存位置
     * 如：  /opt/upload
     * @return
     */
    protected abstract String getQRSavePath();

    /**
     * 新建分享
     * @param t
     * @return
     */
    @Override
    public ResponseEntity<Result> create(@RequestBody @Validated(InsertValid.class) T t) {

        if(StringUtils.isEmpty(getRootURL()))
        {
            throw new BaseException(ShareExceptionEnum.SHARE_ROOT_URL_IS_NULL);
        }

        if(StringUtils.isEmpty(getQRSavePath()))
        {
            throw new BaseException(ShareExceptionEnum.QR_SAVEPATH_IS_NULL);
        }

        if(t!=null)
        {
            t.setId(UUIDUtil.getUUID());
            t.setShareBy(BaseContextHandler.getUserId());
            return doSuccess(CommonConstants.SAVE_SUCCESS,service.insertAndCreateQRCoder(t,getRootURL()+UUIDUtil.getUUID(),getQRSavePath()));
        }
        else{
            throw new BaseException(BaseExceptionEnum.REQUEST_NULL);
        }
    }

    /**
     * 根据业务id获取分享实体对象
     * 返回最新一条
     * @param id
     * @return
     */
    @GetMapping("/data/{id}")
    public ResponseEntity<Result> getInfoByDataId(@PathVariable String id) {
        if(id!=null)
        {
            EntityWrapper<T> wrapper = new EntityWrapper<>();
            wrapper.eq(ShareConstants.SHARE_COLUMN_DATA_ID,id);
            List<T> shareList = service.selectList(wrapper);
            if(!CollectionUtils.isEmpty(shareList))
            {
                return doSuccess(CommonConstants.QUERY_SUCCESS,shareList.get(0));
            }
            return doError(CommonConstants.QUERY_ERROR);
        }
        else{
            throw new BaseException(BaseExceptionEnum.REQUEST_NULL);
        }
    }
}
