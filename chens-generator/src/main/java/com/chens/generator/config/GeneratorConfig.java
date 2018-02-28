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
                .setTablePrefix(new String[]{"t_"})
                .setInclude("t_tag")
                .setNaming(NamingStrategy.underline_to_camel);
    }

    /**
     * 自定义包名
     */
    protected void packageConfig() {
        packageConfig.setParent("com.chens.exam.wms")
                .setController("controller")
                .setEntity("entity");
    }


    @Override
    protected void config() {
        globalConfig();
        dataSourceConfig();
        strategyConfig();
        packageConfig();
    }
}
