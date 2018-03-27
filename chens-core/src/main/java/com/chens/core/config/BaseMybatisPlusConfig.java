package com.chens.core.config;

import java.util.ArrayList;
import java.util.List;

import com.baomidou.mybatisplus.entity.GlobalConfiguration;
import com.baomidou.mybatisplus.enums.DBType;
import com.baomidou.mybatisplus.enums.FieldStrategy;
import com.baomidou.mybatisplus.enums.IdType;
import com.baomidou.mybatisplus.mapper.ISqlInjector;
import com.baomidou.mybatisplus.mapper.LogicSqlInjector;
import com.baomidou.mybatisplus.spring.MybatisSqlSessionFactoryBean;
import com.baomidou.mybatisplus.spring.boot.starter.MybatisPlusProperties;
import com.chens.core.constants.CommonConstants;
import com.chens.core.context.BaseContextHandler;
import com.chens.core.enums.YesNoEnum;
import net.sf.jsqlparser.expression.StringValue;
import org.apache.ibatis.plugin.Interceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;

import com.baomidou.mybatisplus.mapper.MetaObjectHandler;
import com.baomidou.mybatisplus.plugins.PaginationInterceptor;
import com.baomidou.mybatisplus.plugins.parser.ISqlParser;
import com.baomidou.mybatisplus.plugins.parser.tenant.TenantHandler;
import com.baomidou.mybatisplus.plugins.parser.tenant.TenantSqlParser;
import com.chens.core.handler.MyMetaObjectHandler;

import net.sf.jsqlparser.expression.Expression;
import net.sf.jsqlparser.expression.LongValue;
import org.springframework.core.io.DefaultResourceLoader;
import org.springframework.core.io.ResourceLoader;
import org.springframework.util.ObjectUtils;
import org.springframework.util.StringUtils;

import javax.sql.DataSource;

/**
 * Mybatis-plus配置
 *
 * @auther songchunlei@qq.com
 * @create 2018/2/12
 */
public abstract class BaseMybatisPlusConfig {

    @Autowired
    private MybatisPlusProperties properties;

    @Autowired
    private DataSource dataSource;

    @Autowired
    private ResourceLoader resourceLoader = new DefaultResourceLoader();

    @Autowired(required = false)
    private Interceptor[] interceptors;


    /**
     * mybatis-plus SQL执行效率插件【生产环境可以关闭】
     * @return
     */
    /*
    @Bean
    public PerformanceInterceptor performanceInterceptor() {
        return new PerformanceInterceptor();
    }
    */

    @Bean
    public MybatisSqlSessionFactoryBean mybatisSqlSessionFactoryBean() {


        MybatisSqlSessionFactoryBean mybatisPlus = new MybatisSqlSessionFactoryBean();
        mybatisPlus.setDataSource(dataSource);
        //mybatisPlus.setVfs(SpringBootVFS.class);

        //填充页面配置
        if (StringUtils.hasText(this.properties.getConfigLocation())) {
            mybatisPlus.setConfigLocation(this.resourceLoader.getResource(this.properties.getConfigLocation()));
        }
        mybatisPlus.setConfiguration(properties.getConfiguration());

        //放入自定义工具
        if (!ObjectUtils.isEmpty(this.interceptors)) {
            mybatisPlus.setPlugins(this.interceptors);
        }


        if (StringUtils.hasLength(this.properties.getTypeAliasesPackage())) {
            mybatisPlus.setTypeAliasesPackage(this.properties.getTypeAliasesPackage());
        }
        if(StringUtils.hasLength(this.properties.getTypeEnumsPackage()))
        {
            mybatisPlus.setTypeEnumsPackage(this.properties.getTypeEnumsPackage());
        }
        if (StringUtils.hasLength(this.properties.getTypeHandlersPackage())) {
            mybatisPlus.setTypeHandlersPackage(this.properties.getTypeHandlersPackage());
        }
        if (!ObjectUtils.isEmpty(this.properties.resolveMapperLocations())) {
            mybatisPlus.setMapperLocations(this.properties.resolveMapperLocations());
        }

        //通用全局设置
        GlobalConfiguration globalConfig = new GlobalConfiguration();
        //是否刷新
        if(properties.getGlobalConfig()!=null)
        {
            globalConfig.setRefresh(properties.getGlobalConfig().getRefreshMapper());
        }
        //数据库类型
        //globalConfig.setDbType(DBType.MYSQL.name());
        //主键类型  0:"数据库ID自增", 1:"用户输入ID",2:"全局唯一ID (数字类型唯一ID)", 3:"全局唯一ID UUID";
        globalConfig.setIdType(IdType.UUID.getKey());
        //字段策略 0:"忽略判断",1:"非 NULL 判断"),2:"非空判断"
        globalConfig.setFieldStrategy(FieldStrategy.NOT_EMPTY.getKey());
        //驼峰下划线转换
        globalConfig.setDbColumnUnderline(true);
        //逻辑删除配置
        globalConfig.setLogicDeleteValue(YesNoEnum.YES.getCode());
        globalConfig.setLogicNotDeleteValue(YesNoEnum.NO.getCode());
        //SQL 解析缓存，开启后多租户 @SqlParser 注解生效
        globalConfig.setSqlParserCache(true);
        //加入自定义注解(开启逻辑删除)
        globalConfig.setSqlInjector(this.getSqlInjector());
        //加入公共字段填充
        globalConfig.setMetaObjectHandler(this.getMetaObjectHandler());
        mybatisPlus.setGlobalConfig(globalConfig);

        return mybatisPlus;
    }


    /**
     * 分页插件
     * @return
     */
    @Bean
    public PaginationInterceptor paginationInterceptor() {
        PaginationInterceptor page = new PaginationInterceptor();
        page.setLocalPage(true);// 开启 PageHelper 的支持


        //测试多租户】 SQL 解析处理拦截器<br>
        //这里固定写成住户 1 实际情况你可以从cookie读取，因此数据看不到 【 麻花藤 】 这条记录（ 注意观察 SQL ）<br>
        List<ISqlParser> sqlParserList = new ArrayList<>();
        TenantSqlParser tenantSqlParser = new TenantSqlParser();
        tenantSqlParser.setTenantHandler(new TenantHandler() {
            @Override
            public Expression getTenantId() {
                //从缓存拿租户
                if(StringUtils.hasLength(BaseContextHandler.getTenantId()))
                {
                    return new StringValue(BaseContextHandler.getTenantId());
                }
                return null;
            }

            @Override
            public String getTenantIdColumn() {
                return CommonConstants.BASE_COLUMN_TENANT_ID;
            }

            @Override
            public boolean doTableFilter(String tableName) {

                boolean flg = CommonConstants.NO_TENANT_TABLENAME.indexOf("["+tableName+"]")!=-1;
                // 这里可以判断是否过滤表
                if (flg) {
                    return true;
                }

                return false;
            }
        });


        sqlParserList.add(tenantSqlParser);
        page.setSqlParserList(sqlParserList);

        //可以用 @SqlParser(filter = true) 注解取消某个mapper下方法的租户信息
        return page;
    }

    /**
     * 注入公共字段自动填充,任选注入方式即可
     * @return
     */
    @Bean
    public MetaObjectHandler getMetaObjectHandler(){
        return new MyMetaObjectHandler();
    }



    /**
     * 注入sql注入器
     */
    @Bean
    public ISqlInjector getSqlInjector(){
        return new LogicSqlInjector();
    }


}
