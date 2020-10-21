package com.example.sandbox_tenant.data;

import org.hibernate.annotations.Filter;
import org.hibernate.annotations.FilterDef;
import org.hibernate.annotations.ParamDef;

import static com.example.sandbox_tenant.data.TenantSupport.TENANT_FILTER_NAME;
import static com.example.sandbox_tenant.data.TenantSupport.TENANT_FILTER_PARAMETER_NAME;


public interface TenantSupport {

    String TENANT_FILTER_NAME = "tenant_filter";
    String TENANT_FILTER_PARAMETER_NAME = "tenantId";
    String TENANT_FIELD_NAME = "tenantId";

//    void setTenantId(String tenantId);

}
