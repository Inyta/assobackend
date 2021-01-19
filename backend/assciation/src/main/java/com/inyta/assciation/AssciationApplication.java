package com.inyta.assciation;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.inyta.assciation.mapper")
public class AssciationApplication {

  public static void main(String[] args) {
    SpringApplication.run(AssciationApplication.class, args);
  }

}

