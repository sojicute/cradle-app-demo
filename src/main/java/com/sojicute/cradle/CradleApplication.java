package com.sojicute.cradle;

import org.hibernate.cfg.Environment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class CradleApplication {
    public static void main(String[] args) {
        SpringApplication.run(CradleApplication.class, args);
    }

}
