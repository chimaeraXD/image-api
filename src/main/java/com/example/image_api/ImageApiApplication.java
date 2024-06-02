package com.example.image_api;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cloud.openfeign.EnableFeignClients;

@EnableFeignClients
@EnableConfigurationProperties
@SpringBootApplication
public class ImageApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(ImageApiApplication.class, args);
	}

}
