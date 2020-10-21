package com.example.sandbox_tenant.data;

import com.example.sandbox_tenant.configuration.TenantContext;
import lombok.RequiredArgsConstructor;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.hibernate.Session;
import javax.persistence.EntityManager;

import static com.example.sandbox_tenant.data.TenantSupport.TENANT_FILTER_NAME;
import static com.example.sandbox_tenant.data.TenantSupport.TENANT_FILTER_PARAMETER_NAME;

@Aspect
@RequiredArgsConstructor
public class TenantRepositoryAspect {

    private final EntityManager entityManager;


//    @Pointcut("execution(public * org.springframework.data.repository.Repository+.*(..))")
//    void isRepository() {
//        /* aspect */
//    }
//
//    @Pointcut(value = "isRepository()")
//    void enableMultiTenancy() {
//        /* aspect */
//    }
//
//    @Around("execution(public * *(..)) && enableMultiTenancy()")
    @Around("execution(public * org.springframework.data.repository.Repository+.*(..))")
    public Object aroundExecution(final ProceedingJoinPoint pjp) throws Throwable {
        Session session = this.entityManager.unwrap(Session.class);
        session.enableFilter(TENANT_FILTER_NAME)
                .setParameter(TENANT_FILTER_PARAMETER_NAME, TenantContext.getCurrentTenant())
                .validate();
        Object result = pjp.proceed();
        // TODO: do we need to disable that filter after execution?
//        session.disableFilter(TENANT_FILTER_NAME);
        return result;
    }

}
