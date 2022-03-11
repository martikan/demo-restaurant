package com.martikan.restaurantapi;

import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import javax.transaction.Transactional;

/**
 * Init integration tests.
 */
@Transactional
@SpringBootTest
@ActiveProfiles("test")
public abstract class RestaurantApiApplicationTests {
}
