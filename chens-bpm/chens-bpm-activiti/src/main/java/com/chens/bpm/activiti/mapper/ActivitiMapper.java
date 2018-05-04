package com.chens.bpm.activiti.mapper;


import com.baomidou.mybatisplus.plugins.pagination.Pagination;
import com.chens.bpm.vo.MyDoneTask;
import com.chens.bpm.vo.MyStartInstance;
import com.chens.bpm.vo.MyTodoTask;

import java.util.List;

/**
 *
 *  Mapper 接口
 *
 * @author wudepeng
 * @create 2018-04-01
 */
public interface ActivitiMapper {

    /**
     * 我的待办
     * @param page
     * @param myTodoTask
     * @return
     */
    List<MyTodoTask> getMyTodoTaskPage(Pagination page, MyTodoTask myTodoTask);


    /**
     * 我的已办
     * @param page
     * @param myDoneTask
     * @return
     */
    List<MyDoneTask> getMyDoneTaskPage(Pagination page, MyDoneTask myDoneTask);



    /**
     * 我的申请/我发起的流程分页
     * @param page
     * @param myStartProcessInstance
     * @return
     */
    List<MyStartInstance> getMyStartProcessInstancePage(Pagination page, MyStartInstance myStartProcessInstance);

}