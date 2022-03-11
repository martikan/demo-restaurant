package com.martikan.restaurantapi.repository;

import com.martikan.restaurantapi.domain.meal.Ingredient;
import org.springframework.stereotype.Repository;

/**
 * Repository for {@link Ingredient} entity.
 */
@Repository
public interface IngredientRepository extends AbstractRepository<Ingredient> {
}
