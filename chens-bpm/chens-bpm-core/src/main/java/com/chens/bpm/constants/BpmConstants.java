package com.chens.bpm.constants;

/**
 * 流程常量
 *
 * @author songchunlei@qq.com
 * @create 2018/4/7
 */
public final class BpmConstants {

    /**
     * 流程定义id
     */
    public static final String KEY_PROCESS_DEFINITION_ID="processDefinitionId";

    /**
     * 流程定义名称
     */
    public static final String KEY_PROCESS_DEFINITION_NAME="processDefinitionName";

    /**
     * 流程定义版本
     */
    public static final String KEY_PROCESS_DEFINITION_VERSION="processDefinitionVersion";

    /**
     * 流程实例id
     */
    public static final String KEY_PROCESS_INSTANCE_ID="processInstanceId";

    /**
     * 发起节点名称
     */
    public static final String PROCESS_START_TASK_NAME="流程发起";

    /**
     * 办理成功
     */
    public static final String PROCESS_TASK_DONE_SUCCESS="办理成功";

    /**
     * 简单类流程-审批节点
     */
    public static final String SIMPLE_PROCESS_TASK_KEY_APPROVE_NODE="approveNode";

    /**
     * 简单类流程-发起人修改
     */
    public static final String SIMPLE_PROCESS_TASK_KEY_START_MODIFY="startNode";

    /**
     * 简单类流程-审批通过
     */
    public static final String SIMPLE_PROCESS_TASK_KEY_PASS="pass";

}
