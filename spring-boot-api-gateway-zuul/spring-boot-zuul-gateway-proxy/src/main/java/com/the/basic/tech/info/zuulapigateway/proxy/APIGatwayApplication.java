package com.the.basic.tech.info.zuulapigateway.proxy;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;
import org.springframework.context.annotation.Bean;

import com.the.basic.tech.info.zuulapigateway.proxy.filters.ErrorFilter;
import com.the.basic.tech.info.zuulapigateway.proxy.filters.PostFilter;
import com.the.basic.tech.info.zuulapigateway.proxy.filters.PreFilter;
import com.the.basic.tech.info.zuulapigateway.proxy.filters.RouteFilter;

@SpringBootApplication
@EnableZuulProxy
public class APIGatwayApplication {

	public static void main(String[] args) {
		SpringApplication.run(APIGatwayApplication.class, args);
	}

	@Bean
	public PreFilter preFilter() {
		return new PreFilter();
	}
	@Bean
	public PostFilter postFilter() {
		return new PostFilter();
	}
	@Bean
	public ErrorFilter errorFilter() {
		return new ErrorFilter();
	}
	@Bean
	public RouteFilter routeFilter() {
		return new RouteFilter();
	}
}
