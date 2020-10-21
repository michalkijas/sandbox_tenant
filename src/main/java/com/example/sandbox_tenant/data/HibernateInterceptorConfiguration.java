package com.example.sandbox_tenant.data;

import lombok.RequiredArgsConstructor;
import org.hibernate.Interceptor;
import org.springframework.boot.autoconfigure.orm.jpa.HibernatePropertiesCustomizer;
import java.util.Map;

@RequiredArgsConstructor
class HibernateInterceptorConfiguration implements HibernatePropertiesCustomizer {

    private final Interceptor tenantInterceptor;


    @Override
    public void customize(final Map<String, Object> hibernateProperties) {
        hibernateProperties.put("hibernate.session_factory.interceptor", this.tenantInterceptor);
    }

}
