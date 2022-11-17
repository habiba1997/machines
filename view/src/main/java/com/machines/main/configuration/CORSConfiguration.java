package com.machines.main.configuration;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import javax.annotation.PostConstruct;

@Configuration
@EnableWebMvc
public class CORSConfiguration implements WebMvcConfigurer {

	@PostConstruct
	void init(){
		System.out.println("init conf");
	}
	@Override
	public void addCorsMappings(final CorsRegistry registry) {
		registry.addMapping("/**");

	}

}
