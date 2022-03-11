package com.martikan.restaurantapi.service;

import com.martikan.restaurantapi.domain.role.Role;
import com.martikan.restaurantapi.domain.role.RoleName;
import com.martikan.restaurantapi.exception.ResourceNotFoundException;
import com.martikan.restaurantapi.repository.RoleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

/**
 * Implementation for {@link RoleService}.
 */
@RequiredArgsConstructor
@Transactional
@Service
public class RoleServiceImpl implements RoleService {

    private final RoleRepository roleRepository;

    /**
     * Find role by name.
     * @param name {@link RoleName}
     * @return {@link Role}
     */
    @Override
    public Role findRoleByName(final RoleName name) {
        return roleRepository.findByName(name)
                .orElseThrow(() -> new ResourceNotFoundException(Role.class.getSimpleName(), "name", name));
    }
}
