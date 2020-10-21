package com.example.sandbox_tenant.data;

import org.hibernate.Interceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
class HibernateConfiguration {

    @Bean
    Interceptor hibernateTenantInterceptor() {
        return new TenantSupportInterceptor();
    }

    @Bean
    HibernateInterceptorConfiguration hibernateInterceptorConfiguration(Interceptor interceptor) {
        return new HibernateInterceptorConfiguration(interceptor);
    }

}
