package com.chens.tag.service;

import com.chens.tag.entity.TagRel;
import com.baomidou.mybatisplus.service.IService;

import java.util.List;

/**
 *
 *  服务接口
 *
 * @author chunlei.song@live.com
 * @create 2018-04-27
 */
public interface ITagRelService extends IService<TagRel> {

    /**
     * 根据数据id，标签列表保存标签关联关系
     * @param dataId 数据id
     * @param tagIdList 标签id类型
     * @return
     */
	boolean saveByDataIdAndTagList(String dataId,List<String> tagIdList);

}
