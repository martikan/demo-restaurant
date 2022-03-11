package com.martikan.restaurantapi.service;

import com.martikan.restaurantapi.dto.authentication.SignUpDTO;
import com.martikan.restaurantapi.dto.user.EmailAvailabilityDTO;
import com.martikan.restaurantapi.dto.user.UserDTO;
import org.springframework.security.core.userdetails.UserDetailsService;

/**
 * Service for {@link com.martikan.restaurantapi.domain.user.User} and authentication's stuffs.
 */
public interface UserService extends UserDetailsService {

    EmailAvailabilityDTO checkEmailAvailability(final String email);

    UserDTO signUp(final SignUpDTO signUpRequest);

}
