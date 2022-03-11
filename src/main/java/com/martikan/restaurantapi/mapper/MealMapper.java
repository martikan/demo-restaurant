package com.martikan.restaurantapi.mapper;

import com.martikan.restaurantapi.domain.meal.Meal;
import com.martikan.restaurantapi.dto.meal.MealDTO;
import org.mapstruct.Mapper;

/**
 * Mapper for {@link Meal} and {@link MealDTO}.
 */
@Mapper(componentModel = "spring")
public interface MealMapper extends AbstractMapper<Meal, MealDTO> {
}
