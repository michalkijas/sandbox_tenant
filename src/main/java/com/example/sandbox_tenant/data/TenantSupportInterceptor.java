package com.example.sandbox_tenant.data;

import com.example.sandbox_tenant.configuration.TenantContext;
import org.hibernate.EmptyInterceptor;
import org.hibernate.type.Type;
import java.io.Serializable;

public class TenantSupportInterceptor extends EmptyInterceptor {

    @Override
    public void onDelete(Object entity, Serializable id, Object[] state, String[] propertyNames, Type[] types) {
        applyTenantId(entity, state, propertyNames);
    }

    @Override
    public boolean onFlushDirty(Object entity, Serializable id, Object[] currentState, Object[] previousState, String[] propertyNames, Type[] types) {
        return applyTenantId(entity, currentState, propertyNames);
    }

    @Override
    public boolean onSave(Object entity, Serializable id, Object[] state, String[] propertyNames, Type[] types) {
        return applyTenantId(entity, state, propertyNames);
    }

    private boolean applyTenantId(Object entity, Object[] state, String[] propertyNames) {
        if (entity instanceof TenantSupport) {
            for (int index = 0; index < propertyNames.length; index++) {
                if (propertyNames[index].equals(TenantSupport.TENANT_FIELD_NAME)) {
                    state[index] = TenantContext.getCurrentTenant();
                    return true;
                }
            }
            throw new ClassCastException();
        }
        return false;
    }

// Alternative - using method from interface - tenantId isn't visible in update query
//        if (entity instanceof TenantSupport) {
//            ((TenantSupport) entity).setTenantId(TenantContext.getCurrentTenant());
//        }
//        return false;

}
