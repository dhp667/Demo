package com.example.demo;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.Banner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.annotation.ComponentScan;import com.fasterxml.jackson.core.sym.Name;

@SpringBootApplication(exclude = { DataSourceAutoConfiguration.class }/* , scanBasePackages="com.example.entity" */)
@EnableAutoConfiguration(exclude = { DataSourceAutoConfiguration.class })
public class DemoApplication {


	public static void main(String[] args) {
		
		 SpringApplication.run(DemoApplication.class, args);
		/*
		 * SpringApplication application = new SpringApplication(DemoApplication.class);
		 * application.setBannerMode(Banner.Mode.OFF); application.run(args);
		 */
		

	}
}
