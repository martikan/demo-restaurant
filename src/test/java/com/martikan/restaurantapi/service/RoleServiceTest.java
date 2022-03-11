package com.martikan.restaurantapi.service;

import com.martikan.restaurantapi.domain.role.Role;
import com.martikan.restaurantapi.domain.role.RoleName;
import com.martikan.restaurantapi.exception.ResourceNotFoundException;
import com.martikan.restaurantapi.repository.RoleRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
public class RoleServiceTest {

    @Mock
    private RoleRepository roleRepository;

    @InjectMocks
    private RoleServiceImpl roleService;

    @Test
    void findRoleByNameTest_RoleIsExist() {

        // Arrange
        final var roleEntity = new Role();
        roleEntity.setId(1L);
        roleEntity.setName(RoleName.ROLE_USER);

        when(roleRepository.findByName(RoleName.ROLE_USER)).thenReturn(Optional.of(roleEntity));

        // Act
        final var returnedRole = roleService.findRoleByName(RoleName.ROLE_USER);

        // Assert
        assertEquals(roleEntity, returnedRole);

    }

    @Test
    void findRoleByNameTest_RoleIsNotExist() {

        // Act
        final var thrown = Assertions.assertThrows(ResourceNotFoundException.class, () -> {
            roleService.findRoleByName(RoleName.ROLE_USER);
        });

        // Assert
        assertEquals(String.format("%s not found with %s : '%s'", Role.class.getSimpleName(), "name", RoleName.ROLE_USER.name()), thrown.getMessage());
    }

}
