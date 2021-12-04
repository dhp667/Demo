package com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

import java.io.File;

@SpringBootApplication(exclude = { DataSourceAutoConfiguration.class }/* , scanBasePackages="com.example.entity" */)
//@EnableAutoConfiguration(exclude = { DataSourceAutoConfiguration.class })
public class DemoApplication {
	{
		String curr = System.getProperty("user.dir");
		File file = new File(curr);
		System.out.println(file.getParent());
	}

	public static void main(String[] args) {

		 SpringApplication.run(DemoApplication.class, args);
		/*
		 * SpringApplication application = new SpringApplication(DemoApplication.class);
		 * application.setBannerMode(Banner.Mode.OFF); application.run(args);
		 */

	}


}