package com.chens.tag.controller;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.chens.core.constants.CommonConstants;
import com.chens.core.util.StringUtils;
import com.chens.core.vo.PageVo;
import com.chens.core.vo.QueryPageEntity;
import com.chens.core.vo.Result;
import com.chens.tag.entity.Tag;
import com.chens.tag.service.ITagService;
import com.chens.core.web.BaseWebController;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

/**
 * 资源标签控制器
 *
 * @author songchunlei@qq.com
 * @create 2018/4/3
 */
public class AbstractTagController extends BaseWebController<ITagService,Tag> {


    /**
     * 根据分页对象查询
     * @param spage
     * @return
     */
    @Override
    @PostMapping("/pagelist")
    public ResponseEntity<Result> pagelist(@RequestBody QueryPageEntity<Tag> spage){
        //获取页面参数
        PageVo pageVo = spage.getPage();
        //获取查询参数
        Tag search = spage.getSearch();
        //创建查询条件
        Page<Tag> page = this.createPage(spage);
        //查询各字段

        Tag query = new Tag();
        if(StringUtils.isNotEmpty(search.getClassId()))
        {
            query.setClassId(search.getClassId());
        }
        EntityWrapper<Tag> wrapper = new EntityWrapper<Tag>(query);
        if(StringUtils.isNotEmpty(search.getComment()))
        {
            wrapper.or().like("comment",search.getComment());
        }
        if(StringUtils.isNotEmpty(search.getTagName()))
        {
            wrapper.or().like("tag_name",search.getTagName());
        }
        wrapper.orderBy("update_time desc");
        return  doSuccess(CommonConstants.QUERY_SUCCESS,service.selectPage(page,wrapper));
    }

}
