package com.martikan.restaurantapi.repository;

import com.martikan.restaurantapi.domain.role.Role;
import com.martikan.restaurantapi.domain.role.RoleName;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * Repository for {@link Role} entity.
 */
@Repository
public interface RoleRepository extends AbstractRepository<Role> {

    Optional<Role> findByName(RoleName roleName);

}
