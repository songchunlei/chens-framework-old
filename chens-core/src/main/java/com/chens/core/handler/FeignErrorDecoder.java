package com.chens.core.handler;

import com.alibaba.fastjson.JSONObject;
import com.chens.core.exception.BaseException;
import com.chens.core.util.StringUtils;
import feign.Response;
import feign.Util;
import feign.codec.ErrorDecoder;

import java.io.IOException;

/**
 * Feign异常重新处理
 *
 * @auther songchunlei@qq.com
 * @create 2018/4/6
 */
public class FeignErrorDecoder  implements ErrorDecoder {
    @Override
    public Exception decode(String methodKey, Response response) {
        try {
            // 这里直接拿到我们想过的异常信息
            String message = Util.toString(response.body().asReader());
            JSONObject obj = JSONObject.parseObject(message);
            Integer errCode = (Integer) obj.get("code");
            String errMsg = (String)obj.get("message");
            //可能存在msg的情况
            if(StringUtils.isEmpty(errMsg))
            {
                errMsg = (String)obj.get("msg");
            }
            return new BaseException(errCode,errMsg);
        } catch (IOException ignored) {
        }
        return decode(methodKey, response);
    }
}
