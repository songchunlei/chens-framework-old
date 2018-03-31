package com.chens.workflow.config;

import org.activiti.spring.SpringProcessEngineConfiguration;
import org.activiti.spring.boot.ProcessEngineConfigurationConfigurer;
import org.springframework.stereotype.Component;

@Component
public class ProcessConfig implements ProcessEngineConfigurationConfigurer {

	@Override
	public void configure(SpringProcessEngineConfiguration configuration) {
		configuration.setActivityFontName("宋体");
		configuration.setLabelFontName("宋体");
		configuration.setAnnotationFontName("宋体");
	}

}
