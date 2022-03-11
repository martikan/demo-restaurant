package com.martikan.restaurantapi.mapper;

import com.martikan.restaurantapi.domain.user.User;
import com.martikan.restaurantapi.dto.user.UserDTO;
import org.mapstruct.Mapper;

/**
 * Mapper for {@link User} and {@link UserDTO}.
 */
@Mapper(componentModel = "spring")
public interface UserMapper extends AbstractMapper<User, UserDTO> {
}
