package com.chens.generator.config;

import com.baomidou.mybatisplus.enums.IdType;
import com.baomidou.mybatisplus.generator.config.TemplateConfig;
import com.baomidou.mybatisplus.generator.config.rules.DbType;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;

/**
 * @Author:chunlei.song@live.com
 * @Description:
 * @Date Create in 14:53 2018-2-28
 * @Modified By:
 */
public class GeneratorConfig extends AbstractGeneratorConfig{

    private static final String BASE_SUPER_CONTROLLER_CLASS_NAME ="com.chens.core.web.BaseWebController";
    private static final String BASE_SUPER_ENTITY_CLASS_NAME ="com.chens.core.vo.BaseEntity";
    private static final String BASE_SUPER_WF_CONTROLLER_CLASS_NAME ="om.chens.bpm.controller.WfBaseController";
    private static final String BASE_SUPER_WF_ENTITY_CLASS_NAME ="com.chens.bpm.vo.WfBaseEntity";
    private static final String BASE_SUPER_WF_SERVICE_CLASS_NAME ="com.chens.bpm.service.IWfEngineService";
    private static final String BASE_SUPER_WF_SERVICEIMPL_CLASS_NAME ="com.chens.bpm.service.impl.WfBaseServiceImpl";

    //是否是参与流程的实体
    private boolean isWfEntity = false;

    /**
     * 全局配置
     */
    protected void globalConfig() {
        globalConfig.setActiveRecord(false)
                .setAuthor("chunlei.song@live.com")
                .setOutputDir("c:\\zLife")
                .setFileOverride(true)
                .setOpen(false)
                .setEnableCache(false)
                .setBaseResultMap(false)
                .setBaseColumnList(true)
                .setIdType(IdType.UUID);
    }

    /**
     * 数据库配置
     */
    protected void dataSourceConfig() {
        dataSourceConfig.setDbType(DbType.MYSQL)
                .setUrl("jdbc:mysql://chens:3306/qyexam?characterEncoding=utf8")
                .setUsername("root")
                .setPassword("abc@123456")
                .setDriverName("com.mysql.jdbc.Driver");
    }

    /**
     * 策略配置
     */
    protected void strategyConfig() {
        //strategyConfig.setTablePrefix(new String[]{"xx_"});// 此处可以修改为您的表前缀
        strategyConfig
                .setTablePrefix(new String[]{"t_"})
                .setInclude("t_tag_class")
                .setNaming(NamingStrategy.underline_to_camel)
                .setLogicDeleteFieldName("is_delete");//逻辑删除字段

        if(isWfEntity)
        {
            strategyConfig
                    .setSuperControllerClass(BASE_SUPER_WF_CONTROLLER_CLASS_NAME)
                    .setSuperEntityClass(BASE_SUPER_WF_ENTITY_CLASS_NAME)
                    .setSuperServiceClass(BASE_SUPER_WF_SERVICE_CLASS_NAME)
                    .setSuperServiceImplClass(BASE_SUPER_WF_SERVICEIMPL_CLASS_NAME);
        }
        else
        {
            strategyConfig
                    .setSuperControllerClass(BASE_SUPER_CONTROLLER_CLASS_NAME)
                    .setSuperEntityClass(BASE_SUPER_ENTITY_CLASS_NAME);
        }
    }

    /**
     * 自定义包名
     */
    //packageConfig.setParent("com.chens.exam.wms")
    //.setController("controller")
    //.setEntity("entity");
    protected void packageConfig() {
        packageConfig.setParent(null)
                .setController("com.chens.exam.book.controller")
                .setMapper("com.chens.exam.book.mapper")
                .setService("com.chens.exam.book.service")
                .setServiceImpl("com.chens.exam.book.service.impl")
                .setEntity("com.chens.exam.core.entity.book");
    }

    /**
     * 自定义模板配置
     **/
    protected void templateConfig() {

        /* 暂时不用改变实体类
        if(isWfEntity)
        {
            templateConfig.setMapper("/templates/entity.java");
        }

        templateConfig.setMapper("/templates/service.java");
        templateConfig.setMapper("/templates/serviceImpl.java");
        templateConfig.setMapper("/templates/mapper.java");
        templateConfig.setMapper("/templates/mapper.xml");
        templateConfig.setMapper("/templates/controller.java");
        */


    }


    @Override
    protected void config() {
        globalConfig();
        dataSourceConfig();
        strategyConfig();
        packageConfig();
        templateConfig();
    }
}
