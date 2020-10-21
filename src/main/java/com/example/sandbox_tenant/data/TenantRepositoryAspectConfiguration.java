package com.example.sandbox_tenant.data;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import javax.persistence.EntityManager;

@Configuration
class TenantRepositoryAspectConfiguration {

    @Bean
    TenantRepositoryAspect tenantRepositoryAspect(EntityManager entityManager) {
        return new TenantRepositoryAspect(entityManager);
    }

}
