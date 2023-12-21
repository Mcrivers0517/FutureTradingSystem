package cn.edu.zjut.fts;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("cn.edu.zjut.fts.mapper")
public class FtsApplication {

	public static void main(String[] args) {
		SpringApplication.run(FtsApplication.class, args);
	}

}
