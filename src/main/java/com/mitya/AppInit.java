package com.mitya;

import javafx.application.Application;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
@SpringBootApplication
public class AppInit extends  SpringBootServletInitializer {

    public static void main(String[] args) throws Throwable {
        SpringApplication.run(AppInit.class, args);
    }
//    @Override
//    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
//        return application.sources(AppInit.class);
//    }
}
