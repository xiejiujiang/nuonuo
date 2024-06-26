package com.example.nuonuo;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;

@SpringBootApplication
@MapperScan(value = "com.example.nuonuo.mapper")
public class NuonuoApplication {

    public static void main(String[] args) {
        SpringApplication.run(NuonuoApplication.class, args);
    }
}
