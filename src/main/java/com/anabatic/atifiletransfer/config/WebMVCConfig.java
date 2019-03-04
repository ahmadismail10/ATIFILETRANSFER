package com.anabatic.atifiletransfer.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@Configuration
public class WebMVCConfig extends WebMvcConfigurerAdapter {
	
	@Override
	public void addViewControllers(ViewControllerRegistry routes) {
		routes.addViewController("/403").setViewName("403");
		routes.addViewController("/login").setViewName("login");
	}
	
}
