package com.mitya;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;


@SpringBootApplication
@ComponentScan("com.mitya")
public class AppInit extends  SpringBootServletInitializer {

    public static void main(String[] args) throws Throwable {
        SpringApplication.run(AppInit.class, args);
    }

//    @Override
//    protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
//        return  builder.sources(AppInit.class);
//    }
}
