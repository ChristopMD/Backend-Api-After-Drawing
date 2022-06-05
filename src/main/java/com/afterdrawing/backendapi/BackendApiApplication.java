package com.afterdrawing.backendapi;

import org.modelmapper.ModelMapper;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.stereotype.Repository;
import org.springframework.web.client.RestTemplate;


import java.util.Locale;


@EnableJpaAuditing
@SpringBootApplication
@ComponentScan("com.afterdrawing.backendapi.core")
@ComponentScan("com.afterdrawing.backendapi")
@Repository
@EnableAsync
public class BackendApiApplication {

    public static void main(String[] args) {
        SpringApplication.run(BackendApiApplication.class, args);
    }

    @Bean
    public ModelMapper modelMapper() { return new ModelMapper(); }
    @Bean
    public RestTemplate restTemplate(){return new RestTemplate();}

}
