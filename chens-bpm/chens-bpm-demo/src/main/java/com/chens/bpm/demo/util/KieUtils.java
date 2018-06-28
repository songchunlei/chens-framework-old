package com.chens.bpm.demo.util;

import org.kie.api.runtime.KieContainer;

/**
 * @author songchunlei@qq.com
 * @create 2018/6/17
 */
public class KieUtils {

    private static KieContainer kieContainer;

    public static KieContainer getKieContainer() {
        return kieContainer;
    }

    public static void setKieContainer(KieContainer kieContainer) {
        KieUtils.kieContainer = kieContainer;
    }
}
