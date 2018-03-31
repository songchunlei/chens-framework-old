package com.chens.gateway.configuation;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
// spring会将对应配置项的值注入进来的
@ConfigurationProperties("chens.zuul.tokenFilter")
public class GatewayConfiguration {
	// 这个列表存的是routeId。这个列表里面的路由，不需要进行token校验，在TokenValidataFilter中会用到
	private List<String> noAuthenticationRoutes;

	public List<String> getNoAuthenticationRoutes() {
		return noAuthenticationRoutes;
	}

	public void setNoAuthenticationRoutes(List<String> noAuthenticationRoutes) {
		this.noAuthenticationRoutes = noAuthenticationRoutes;
	}

	
}
