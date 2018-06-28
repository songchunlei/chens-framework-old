package com.chens.bpm.demo.service;

import org.kie.api.KieServices;
import org.springframework.stereotype.Service;

/**
 * 规则服务
 *
 * @author songchunlei@qq.com
 * @create 2018/6/17
 */
@Service
public class RuleService {


    private String loadRules() {
        // 从数据库加载的规则
        return "package plausibcheck.adress\n\n rule \"Postcode 6 numbers\"\n\n    when\n  then\n        System.out.println(\"规则2中打印日志：校验通过!\");\n end";

    }

    private KieServices getKieServices() {
        return KieServices.Factory.get();
    }


}
