package com.chens.tag.service.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.chens.tag.entity.TagRel;
import com.chens.tag.mapper.TagRelMapper;
import com.chens.tag.service.ITagRelService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * 
 *  服务实现类
 *
 * @author chunlei.song@live.com
 * @create 2018-04-27
 */
@Service
public class TagRelServiceImpl extends ServiceImpl<TagRelMapper, TagRel> implements ITagRelService {

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean saveByDataIdAndTagList(String dataId, List<String> tagIdList) {

        //1.先删除原标签关系
        TagRel tagRelDelete = new TagRel();
        tagRelDelete.setDataId(dataId);
        this.delete(new EntityWrapper<>(tagRelDelete));

        //2.新增标签关联关系
        List<TagRel> tagRels = new ArrayList<>();
        for (String tagId:tagIdList) {
            tagRels.add(new TagRel(dataId,tagId));
        }
        if(!CollectionUtils.isEmpty(tagRels))
        {
            this.insertBatch(tagRels);
        }
        return true;
    }
}
