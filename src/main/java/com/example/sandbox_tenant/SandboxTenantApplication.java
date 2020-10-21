package com.example.sandbox_tenant;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
//@SpringBootApplication(scanBasePackageClasses = SandboxTenantApplication.class)
public class SandboxTenantApplication {

    public static void main(String[] args) {
        SpringApplication.run(SandboxTenantApplication.class, args);
    }

}
