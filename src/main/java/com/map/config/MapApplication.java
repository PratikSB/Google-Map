package com.map.config;

import java.util.Calendar;

import org.apache.log4j.Logger;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@ComponentScan(basePackages = { "com.map" })
@SpringBootApplication
public class MapApplication {

	private static final Logger logger = Logger.getLogger(MapApplication.class);
	public static void main(String[] args) {
		SpringApplication.run(MapApplication.class, args);
		
		logger.info("Server Started : " + Calendar.getInstance().getTime());
		
	}
}
