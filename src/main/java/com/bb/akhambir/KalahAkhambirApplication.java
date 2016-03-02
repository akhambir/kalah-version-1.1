package com.bb.akhambir;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan("com.bb.akhambir.*")
public class KalahAkhambirApplication {

	public static void main(String[] args) {
		SpringApplication.run(KalahAkhambirApplication.class, args);
	}
}
