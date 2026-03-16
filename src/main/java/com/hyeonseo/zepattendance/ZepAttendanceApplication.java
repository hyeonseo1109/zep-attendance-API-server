package com.hyeonseo.zepattendance;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing
@SpringBootApplication
public class ZepAttendanceApplication {

    public static void main(String[] args) {
        SpringApplication.run(ZepAttendanceApplication.class, args);
    }

}
