package com.vishal.bfh_qualifier_java;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.vishal.bfh_qualifier_java.service.WebhookService;

@SpringBootApplication
public class BfhQualifierJavaApplication implements CommandLineRunner {

    @Autowired
    private WebhookService webhookService;

    public static void main(String[] args) {
        SpringApplication.run(BfhQualifierJavaApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        webhookService.executeFlow();
    }
}
