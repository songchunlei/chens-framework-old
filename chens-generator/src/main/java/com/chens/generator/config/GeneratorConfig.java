package com.chens.generator.config;

import com.baomidou.mybatisplus.enums.IdType;
import com.baomidou.mybatisplus.generator.config.rules.DbType;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;

/**
 * @Author:chunlei.song@live.com
 * @Description:
 * @Date Create in 14:53 2018-2-28
 * @Modified By:
 */
public class GeneratorConfig extends AbstractGeneratorConfig{

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
                //.setInclude("sys_dict","sys_dict_type")
                .setNaming(NamingStrategy.underline_to_camel);
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
                .setEntity("com.chens.core.entity.sys");
    }


    @Override
    protected void config() {
        globalConfig();
        dataSourceConfig();
        strategyConfig();
        packageConfig();
    }
}
