package com.example.sandbox_tenant.configuration;

public class TenantContext {

    public static final String TENANT_ID = "VALID_TENANT_ID_FROM__TENANT_CONTEXT";


    public static String getCurrentTenant() {
        return TENANT_ID;
    }

}
