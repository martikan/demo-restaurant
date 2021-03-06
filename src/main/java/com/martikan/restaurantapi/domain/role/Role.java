package com.martikan.restaurantapi.domain.role;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.martikan.restaurantapi.domain.AbstractEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.springframework.security.core.GrantedAuthority;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

/**
 * Entity for `roles` table.
 */
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Entity
@Table(name = "roles")
public class Role extends AbstractEntity implements GrantedAuthority {

    private static final long serialVersionUID = -707782628699796505L;

    @Enumerated(EnumType.STRING)
    @NotNull
    @Column
    private RoleName name;

    @JsonIgnore
    @Override
    public String getAuthority() {
        return name.name();
    }

}
