package com.chens.generator.config;

import com.baomidou.mybatisplus.generator.AutoGenerator;
import com.baomidou.mybatisplus.generator.config.DataSourceConfig;
import com.baomidou.mybatisplus.generator.config.GlobalConfig;
import com.baomidou.mybatisplus.generator.config.PackageConfig;
import com.baomidou.mybatisplus.generator.config.StrategyConfig;


/**
 * @Author:chunlei.song@live.com
 * @Description:
 * @Date Create in 14:40 2018-2-28
 * @Modified By:
 */
public abstract class AbstractGeneratorConfig {

    /**
     * mybatis-plus代码生成器配置
     */

    GlobalConfig globalConfig = new GlobalConfig();

    DataSourceConfig dataSourceConfig = new DataSourceConfig();

    StrategyConfig strategyConfig = new StrategyConfig();

    PackageConfig packageConfig = new PackageConfig();


    /**
     * 自定义配置
     */
    protected abstract void config();

    /**
     * 删除不必要的代码
     */
    public void destory() {

    }

    /**
     *
     */
    public AbstractGeneratorConfig() {
    }

    /**
     * 运行执行命令
     */
    public void doGenerate() {
        config();
        new AutoGenerator().setGlobalConfig(globalConfig)
                .setDataSource(dataSourceConfig)
                .setStrategy(strategyConfig)
                .setPackageInfo(packageConfig)
                .execute();
        destory();
    }


}
