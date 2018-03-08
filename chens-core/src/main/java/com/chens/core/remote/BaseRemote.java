package com.chens.core.remote;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.service.IService;
import com.chens.core.vo.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 通用接口类
 * 参考了 ace-admin的查询方法
 * @auther songchunlei@qq.com
 * @create 2018/3/8
 */
public class BaseRemote<Entity> {
    @Autowired
    protected IService service;

    @RequestMapping(value = "",method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<Result> add(@RequestBody Entity entity){
        service.insert(entity);
        return ResponseHelper.doSuccess();
    }

    @RequestMapping(value = "/{id}",method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity<Result> get(@PathVariable int id){
        return ResponseHelper.doSuccess(service.selectById(id));
    }

    @RequestMapping(value = "/{id}",method = RequestMethod.PUT)
    @ResponseBody
    public ResponseEntity<Result> update(@RequestBody Entity entity){
        service.updateById(entity);
        return ResponseHelper.doSuccess();
    }
    @RequestMapping(value = "/{id}",method = RequestMethod.DELETE)
    @ResponseBody
    public ResponseEntity<Result> remove(@PathVariable int id){
        service.deleteById(id);
        return ResponseHelper.doSuccess();
    }

    @RequestMapping(value = "/list",method = RequestMethod.GET)
    @ResponseBody
    public List<Entity> all(@RequestBody Entity entity){
        return service.selectList(new EntityWrapper(entity));
    }

}
