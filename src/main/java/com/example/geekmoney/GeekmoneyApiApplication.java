package com.example.geekmoney;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

import com.example.geekmoney.config.property.GeekmoneyApiProperty;

@SpringBootApplication
@EnableConfigurationProperties(GeekmoneyApiProperty.class)
public class GeekmoneyApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(GeekmoneyApiApplication.class, args);
	}
}
