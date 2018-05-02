package com.sashaspringboot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.telegram.telegrambots.ApiContextInitializer;
import org.telegram.telegrambots.starter.EnableTelegramBots;


@SpringBootApplication
@EnableTelegramBots
public class MyWebApplication extends SpringBootServletInitializer {
    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(MyWebApplication.class);
    }

    public static void main(String[] args) throws Exception {
        ApiContextInitializer.init();
        SpringApplication.run(MyWebApplication.class, args);
    }
}

