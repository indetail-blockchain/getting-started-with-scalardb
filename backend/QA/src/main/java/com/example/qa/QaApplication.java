package com.example.qa;

import com.example.qa.security.WebSecurityConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;

@SpringBootApplication
@Import(WebSecurityConfig.class)
public class QaApplication {
  public static void main(String[] args) {
    SpringApplication.run(QaApplication.class, args);
  }
}
