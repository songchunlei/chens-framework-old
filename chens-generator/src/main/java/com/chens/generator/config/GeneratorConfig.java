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
                .setIdType(IdType.ID_WORKER);
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
                //.setTablePrefix(new String[]{"t_"})
                .setInclude("sys_notice","sys_dict")
                .setNaming(NamingStrategy.underline_to_camel)
                .setSuperControllerClass(BASE_SUPER_CONTROLLER_CLASS_NAME)
                .setSuperEntityClass(BASE_SUPER_ENTITY_CLASS_NAME)
                .setLogicDeleteFieldName("is_delete");//逻辑删除字段
    }

    /**
     * 自定义包名
     */
    //packageConfig.setParent("com.chens.exam.wms")
    //.setController("controller")
    //.setEntity("entity");
    protected void packageConfig() {
        packageConfig.setParent(null)
                .setController("com.chens.admin.web.controller")
                .setMapper("com.chens.admin.web.mapper")
                .setService("com.chens.admin.web.service")
                .setServiceImpl("com.chens.admin.web.service.impl")
                .setEntity("com.chens.core.entity");
    }

    /**
     * 自定义模板配置
     **/
    protected void templateConfig() {
        /*
        templateConfig.setMapper("/templates/mapper.java");
        源码位置
        private String entity = "/templates/entity.java";
        private String service = "/templates/service.java";
        private String serviceImpl = "/templates/serviceImpl.java";
        private String mapper = "/templates/mapper.java";
        private String xml = "/templates/mapper.xml";
        private String controller = "/templates/controller.java";
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
