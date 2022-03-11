package com.martikan.restaurantapi.service;

import com.martikan.restaurantapi.domain.role.Role;
import com.martikan.restaurantapi.domain.role.RoleName;
import com.martikan.restaurantapi.domain.user.User;
import com.martikan.restaurantapi.dto.authentication.SignUpDTO;
import com.martikan.restaurantapi.dto.user.EmailAvailabilityDTO;
import com.martikan.restaurantapi.exception.BadRequestException;
import com.martikan.restaurantapi.mapper.UserMapper;
import com.martikan.restaurantapi.repository.RoleRepository;
import com.martikan.restaurantapi.repository.UserRepository;
import com.martikan.restaurantapi.security.UserDetails;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Collections;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
public class UserServiceTest {

    @Mock
    private UserRepository userRepository;

    @Mock
    private UserService userService;

    @Mock
    private RoleRepository roleRepository;

    @Mock
    private RoleService roleService;

    @InjectMocks
    private BCryptPasswordEncoder passwordEncoder;

    @Mock
    private UserMapper userMapper;

    @BeforeEach
    void setUp() {
        roleService = new RoleServiceImpl(roleRepository);
        userService = new UserServiceImpl(userRepository, roleService, passwordEncoder, userMapper);
    }

    @Test
    void checkEmailAvailabilityTest_Available() {

        // Arrange
        final var emailAddress = "asd@info.com";

        final var emailAvailabilityDTO = EmailAvailabilityDTO.builder()
                .emailAvailable(true)
                .build();

        when(userRepository.existsUserByEmail(emailAddress)).thenReturn(!emailAvailabilityDTO.isEmailAvailable());

        // Act
        final var response = userService.checkEmailAvailability(emailAddress);

        // Assert
        assertEquals(response, emailAvailabilityDTO);

    }

    @Test
    void checkEmailAvailabilityTest_NotAvailable() {

        // Arrange
        final var emailAddress = "asd@info.com";

        final var emailAvailabilityDTO = EmailAvailabilityDTO.builder()
                .emailAvailable(false)
                .build();

        when(userRepository.existsUserByEmail(emailAddress)).thenReturn(!emailAvailabilityDTO.isEmailAvailable());

        // Act
        final var response = userService.checkEmailAvailability(emailAddress);

        // Assert
        assertEquals(response, emailAvailabilityDTO);
    }

    @Test
    void loadUserByUsernameTest_UserIsExists() {

        // Arrange
        final var username = "asd@info.com"; // Username = Email

        final var userDetails = UserDetails.builder()
                .id(1L)
                .username(username)
                .build();

        final var userEntity = new User();
        userEntity.setId(userDetails.getId());
        userEntity.setEmail(userDetails.getUsername());

        when(userRepository.findUserByEmail(username)).thenReturn(Optional.of(userEntity));

        // Act
        final var response = userService.loadUserByUsername(username);

        // Assert
        assertEquals(response, userDetails);
    }

    @Test
    void loadUserByUsernameTest_UserIsNotExist() {

        // Arrange
        final var username = "missingEmail@info.com"; // Username = Email

        final var userEntity = new User();
        userEntity.setId(1L);
        userEntity.setEmail(username);

        // Act
        final var thrown = Assertions.assertThrows(UsernameNotFoundException.class, () -> {
            userService.loadUserByUsername(username);
        });

        // Assert
        assertEquals(username, thrown.getMessage());
    }

    @Test
    void signUpTest_UsernameIsAlreadyExists() {

        // Arrange
        final var username = "existingEmail@info.com"; // Username = Email

        final var signUpRequest = SignUpDTO.builder()
                .firstName("Freddy")
                .lastName("Krueger")
                .password("Alma1234")
                .email(username)
                .build();

        when(userRepository.existsUserByEmail(username)).thenReturn(true);

        // Act
        final var thrown = Assertions.assertThrows(BadRequestException.class, () -> {
            userService.signUp(signUpRequest);
        });

        // Assert
        assertEquals("Email address is already exist.", thrown.getMessage());
    }

    @Test
    void signUpTest_Valid() {

        // Arrange
        final var username = "availableEmail@info.com"; // Username = Email

        final var signUpRequest = SignUpDTO.builder()
                .firstName("Freddy")
                .lastName("Krueger")
                .password("Alma1234")
                .email(username)
                .build();

        final var roleEntity = new Role();
        roleEntity.setId(1L);
        roleEntity.setName(RoleName.ROLE_USER);


        final var newUser = new User();
        newUser.setId(1L);
        newUser.setEmail(signUpRequest.getEmail());
        newUser.setPassword(passwordEncoder.encode(signUpRequest.getPassword()));
        newUser.setRoles(Collections.singleton(roleEntity));
        newUser.setFirstName(signUpRequest.getFirstName());
        newUser.setLastName(signUpRequest.getLastName());
        newUser.setPhone(signUpRequest.getPhoneNumber());

        when(roleRepository.findByName(RoleName.ROLE_USER)).thenReturn(Optional.of(roleEntity));
        when(userRepository.existsUserByEmail(username)).thenReturn(false);
        when(userRepository.save(any())).thenReturn(newUser);

        final var newUserDTO = userMapper.toDTO(newUser);

        // Act
        final var createdUser = userService.signUp(signUpRequest);

        // Assert
        assertEquals(createdUser, newUserDTO);
    }

}
