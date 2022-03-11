package com.martikan.restaurantapi.repository;

import com.martikan.restaurantapi.domain.user.User;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * Repository for {@link User} entity.
 */
@Repository
public interface UserRepository extends AbstractRepository<User> {

    @Query("from User u " +
            "left join fetch u.roles r " +
            "where u.email = :email")
    Optional<User> findUserByEmail(String email);

    boolean existsUserByEmail(String email);
}
