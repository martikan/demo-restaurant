package com.martikan.restaurantapi.util;

import com.martikan.restaurantapi.RestaurantApiApplicationTests;
import com.martikan.restaurantapi.security.UserDetails;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Integration test needed because we have to load the context for
 * `${token.expiration}` and `${token.secret}`
 */
@ExtendWith(SpringExtension.class)
public class JwtUtilsIntegrationTest extends RestaurantApiApplicationTests {

    @Autowired
    private JwtUtils jwtUtils;

    private final UserDetails userDetails = UserDetails.builder()
            .id(1L)
            .username("asd@info.com")
            .build();

    @Test
    void parseJwtTest_Valid() {

        // Arrange
        final var username = userDetails.getUsername();

        final var jwtToken = jwtUtils.generateToken(userDetails);

        // Act
        final var parsedUsername = jwtUtils.parseJwt(jwtToken);

        // Assert
        assertEquals(username, parsedUsername);
    }

}
