package com.chens.file.remote;

import com.chens.file.constants.FileFeignName;
import com.chens.file.vo.FileInfo;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * 文件基础服务
 *
 * @author songchunlei@qq.com
 * @create 2018/4/13
 */
@FeignClient(path = FileFeignName.SYS_FILE_RPC,value = FileFeignName.CHENS_FILE_SERVER_NAME)
public interface IFileClient {

    @RequestMapping(value="/upload",method = RequestMethod.POST)
    FileInfo upload(byte[] data);

}
