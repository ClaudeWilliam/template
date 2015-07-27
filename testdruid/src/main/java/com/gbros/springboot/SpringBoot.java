package com.gbros.springboot;

import java.util.HashSet;
import java.util.Set;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@EnableAutoConfiguration
@ComponentScan("com.gbros")
//扫描启动springboot要用到的类
//初始化springboot
public class SpringBoot {
	public static void main(String[] args) {
		SpringApplication app = new SpringApplication(SpringBoot.class);
		app.setWebEnvironment(true);
		app.setShowBanner(false);
		Set<Object> set = new HashSet<Object>();
		app.setSources(set);
		app.run(args);
	}
}
