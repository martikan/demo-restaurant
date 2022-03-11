package com.martikan.restaurantapi.repository;

import com.martikan.restaurantapi.domain.meal.Meal;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Repository for {@link Meal} entity.
 */
@Repository
public interface MealRepository extends AbstractRepository<Meal> {

    @Query("from Meal m " +
            "left join fetch m.ingredients i")
    List<Meal> findAllMeals(final Pageable pageable);

}
