package com.llb.dp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;

import javax.sql.DataSource;

@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class})
public class DpApplication {

	public static void main(String[] args) {
		SpringApplication.run(DpApplication.class, args);
	}
}
