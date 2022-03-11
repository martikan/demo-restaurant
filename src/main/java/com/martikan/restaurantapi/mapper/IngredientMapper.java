package com.martikan.restaurantapi.mapper;

import com.martikan.restaurantapi.domain.meal.Ingredient;
import com.martikan.restaurantapi.dto.meal.IngredientDTO;
import org.mapstruct.Mapper;

/**
 * Mapper for {@link Ingredient} and {@link IngredientDTO}.
 */
@Mapper(componentModel = "spring")
public interface IngredientMapper extends AbstractMapper<Ingredient, IngredientDTO> {
}
