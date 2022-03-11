package com.martikan.restaurantapi.service;

import com.martikan.restaurantapi.domain.meal.Ingredient;
import com.martikan.restaurantapi.domain.meal.Meal;
import com.martikan.restaurantapi.dto.meal.MealDTO;
import com.martikan.restaurantapi.mapper.MealMapperImpl;
import com.martikan.restaurantapi.repository.MealRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
public class MealServiceTest {

    @Mock
    private MealRepository mealRepository;

    private MealService mealService;

    private final List<Meal> mealEntities = new ArrayList<>();

    private final Ingredient ingredient1 = new Ingredient();

    @BeforeEach
    void init() {

        mealService = new MealService(mealRepository, new MealMapperImpl());

        ingredient1.setId(1L);
        ingredient1.setName("pepper");

        final var meal1 = new Meal();
        meal1.setId(1L);
        meal1.setName("Meal 1");
        meal1.setPrice(1.0F);
        meal1.setKcal(100);
        meal1.setIngredients(Collections.singletonList(ingredient1));

        final var meal2 = new Meal();
        meal2.setId(2L);
        meal2.setName("Meal 2");
        meal2.setPrice(2.0F);
        meal2.setKcal(200);
        meal2.setIngredients(Collections.singletonList(ingredient1));

        final var meal3 = new Meal();
        meal3.setId(3L);
        meal3.setName("Meal 3");
        meal3.setPrice(3.0F);
        meal3.setKcal(300);
        meal3.setIngredients(Collections.singletonList(ingredient1));

        mealEntities.add(meal1);
        mealEntities.add(meal2);
        mealEntities.add(meal3);

    }

    @Test
    void findAllTest() {

        // Arrange
        final var mealDTO1 = MealDTO.builder()
                .id(1L)
                .name("Meal 1")
                .price(1.0F)
                .kcal(100)
                .ingredients(Collections.singletonList(ingredient1))
                .build();

        final var mealDTO2 = MealDTO.builder()
                .id(2L)
                .name("Meal 2")
                .price(2.0F)
                .kcal(200)
                .ingredients(Collections.singletonList(ingredient1))
                .build();

        final var mealDTO3 = MealDTO.builder()
                .id(3L)
                .name("Meal 3")
                .price(3.0F)
                .kcal(300)
                .ingredients(Collections.singletonList(ingredient1))
                .build();

        when(mealRepository.findAllMeals(any(Pageable.class))).thenReturn(mealEntities);

        final var mealDTOs = new ArrayList<MealDTO>();
        mealDTOs.add(mealDTO1);
        mealDTOs.add(mealDTO2);
        mealDTOs.add(mealDTO3);

        // Act
        final var resultSet = mealService.findAll(PageRequest.of(0, 20));

        // Assert
        assertEquals(mealDTOs, resultSet);
    }

    @Test
    void findByIdTest() {

        // Arrange
        final var mealDTO1 = MealDTO.builder()
                .id(1L)
                .name("Meal 1")
                .price(1.0F)
                .kcal(100)
                .ingredients(Collections.singletonList(ingredient1))
                .build();

        when(mealRepository.findById(anyLong())).thenReturn(Optional.of(mealEntities.get(0)));

        // Act
        final var result = mealService.findById(1L);

        // Assert
        assertEquals(mealDTO1, result);
    }

    @Test
    void updateTest() {

        // Arrange

        // This is the expected DTO as well
        final var updateToDTO = MealDTO.builder()
                .id(1L)
                .name("Meal 1")
                .price(1.0F)
                .kcal(500)
                .ingredients(Collections.singletonList(ingredient1))
                .build();

        final var savedEntity = new Meal();
        savedEntity.setId(1L);
        savedEntity.setName("Meal 1");
        savedEntity.setPrice(1.0F);
        savedEntity.setKcal(500);
        savedEntity.setIngredients(Collections.singletonList(ingredient1));

        when(mealRepository.findById(anyLong())).thenReturn(Optional.of(mealEntities.get(0)));
        when(mealRepository.save(any(Meal.class))).thenReturn(savedEntity);

        // Act
        final var result = mealService.update(1L, updateToDTO);

        // Assert
        assertEquals(updateToDTO, result);
    }

    @Test
    void saveTest() {

        // Arrange

        final var savedEntity = new Meal();
        savedEntity.setId(1L);
        savedEntity.setName("Meal 1");
        savedEntity.setPrice(1.0F);
        savedEntity.setKcal(100);
        savedEntity.setIngredients(Collections.singletonList(ingredient1));

        final var saveDTO = MealDTO.builder()
                .name("Meal 1")
                .price(1.0F)
                .kcal(100)
                .ingredients(Collections.singletonList(ingredient1))
                .build();

        when(mealRepository.save(any(Meal.class))).thenReturn(savedEntity);

        // Act
        final var result = mealService.save(saveDTO);

        // Assert
        assertEquals(saveDTO, result);
    }

}