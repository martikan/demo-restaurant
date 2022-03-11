package com.martikan.restaurantapi.service;

import com.martikan.restaurantapi.domain.role.Role;
import com.martikan.restaurantapi.domain.role.RoleName;

/**
 * Service for {@link com.martikan.restaurantapi.domain.role.Role} and authentication's stuffs.
 */
public interface RoleService {

    Role findRoleByName(final RoleName name);

}
