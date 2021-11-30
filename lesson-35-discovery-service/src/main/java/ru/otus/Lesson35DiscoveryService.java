package ru.otus;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@SpringBootApplication
@EnableEurekaServer
public class Lesson35DiscoveryService {

    public static void main(String[] args) {
        SpringApplication.run(Lesson35DiscoveryService.class, args);
    }

}
