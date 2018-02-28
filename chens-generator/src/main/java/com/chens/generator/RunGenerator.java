package com.chens.generator;

import com.chens.generator.config.GeneratorConfig;

/**
 * @Author:chunlei.song@live.com
 * @Description:
 * @Date Create in 14:34 2018-2-28
 * @Modified By:
 */
public class RunGenerator {
    public static void main(String[] args) {
        GeneratorConfig generatorConfig = new GeneratorConfig();
        generatorConfig.doGenerate();
    }
}
