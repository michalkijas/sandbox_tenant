package com.example.sandbox_tenant.domain;

import com.example.sandbox_tenant.data.TenantSupport;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Filter;
import org.hibernate.annotations.FilterDef;
import org.hibernate.annotations.FilterJoinTable;
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
@Table(name = "users")
@Entity
@Setter
@Getter
class User implements TenantSupport {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    private Long id;

    private String firstName;

    private String lastName;

    private String comment;

    //    @Getter(value = AccessLevel.PRIVATE)
    //    @Setter(value = AccessLevel.PRIVATE)
    private String tenantId;

    @FilterJoinTable(name = TENANT_FILTER_NAME)
    @JoinTable(
            name = "users_roles",
            joinColumns = {@JoinColumn(name = "user_id")},
            inverseJoinColumns = {@JoinColumn(name = "role_id")}
    )
    @ManyToMany(fetch = FetchType.EAGER)
    private List<Role> roles = new ArrayList<>();

}
