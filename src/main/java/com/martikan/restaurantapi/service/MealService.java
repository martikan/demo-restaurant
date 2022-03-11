package com.martikan.restaurantapi.service;

import com.martikan.restaurantapi.aspect.IsUser;
import com.martikan.restaurantapi.domain.meal.Meal;
import com.martikan.restaurantapi.dto.meal.MealDTO;
import com.martikan.restaurantapi.mapper.MealMapper;
import com.martikan.restaurantapi.repository.MealRepository;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Service for {@link com.martikan.restaurantapi.domain.meal.Meal} entity.
 */
@Service
public class MealService extends AbstractService<Meal, MealDTO> {

    private final MealRepository mealRepository;

    private final MealMapper mealMapper;

    public MealService(final MealRepository mealRepository, final MealMapper mealMapper) {
        super(mealRepository, mealMapper);
        this.mealRepository = mealRepository;
        this.mealMapper = mealMapper;
    }

    @IsUser
    @Override
    public List<MealDTO> findAll(final Pageable pageable) {
        return mealRepository.findAllMeals(pageable)
                .stream()
                .map(mealMapper::toDTO)
                .collect(Collectors.toList());
    }
}
