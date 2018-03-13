package com.chens.admin.remote;


import org.springframework.cloud.netflix.feign.FeignClient;


/**
 * 用户查询接口
 *
 * @auther songchunlei@qq.com
 * @create 2018/3/8
 */
@FeignClient(path = "user", value = "chens-admin-web")
public interface ISysUserClient {


}
