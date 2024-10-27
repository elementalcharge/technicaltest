package org.technicaltest;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories("org.technicaltest.repository")
@ComponentScan(basePackages = {"org.technicaltest", "org.technicaltest.exception"})
public class SpaceShipApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpaceShipApplication.class, args);
    }
}
