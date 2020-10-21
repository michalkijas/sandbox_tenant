package com.example.sandbox_tenant.domain;


import com.example.sandbox_tenant.data.TenantSupport;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Filter;
import org.hibernate.annotations.FilterDef;
import org.hibernate.annotations.ParamDef;
import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

import static com.example.sandbox_tenant.data.TenantSupport.TENANT_FILTER_NAME;
import static com.example.sandbox_tenant.data.TenantSupport.TENANT_FILTER_PARAMETER_NAME;
import static javax.persistence.GenerationType.IDENTITY;


@FilterDef(
        name = TENANT_FILTER_NAME,
        parameters = {@ParamDef(name = "tenantId", type = "string")},
        defaultCondition = "tenant_id = :" + TENANT_FILTER_PARAMETER_NAME
)
@Filter(name = TENANT_FILTER_NAME)
@Table(name = "roles")
@Entity
@Setter
@Getter
class Role implements TenantSupport {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    private Long id;

    private String name;

    private String comment;

    @ManyToMany(mappedBy = "roles")
    private List<User> users = new ArrayList<>();

    //    @Getter(value = AccessLevel.PRIVATE)
//    @Setter(value = AccessLevel.PRIVATE)
    private String tenantId;

}
