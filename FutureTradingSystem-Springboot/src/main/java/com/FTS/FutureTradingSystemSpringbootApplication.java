package com.FTS;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
public class FutureTradingSystemSpringbootApplication {

	public static void main(String[] args) {
		SpringApplication.run(FutureTradingSystemSpringbootApplication.class, args);
	}

}
