package com.martikan.restaurantapi.service;

import com.martikan.restaurantapi.domain.meal.Ingredient;
import com.martikan.restaurantapi.dto.meal.IngredientDTO;
import com.martikan.restaurantapi.mapper.IngredientMapperImpl;
import com.martikan.restaurantapi.repository.IngredientRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
public class IngredientServiceTest {

    @Mock
    private IngredientRepository ingredientRepository;

    private IngredientService ingredientService;

    private final List<Ingredient> ingredientEntities = new ArrayList<>();

    @BeforeEach
    void init() {

        ingredientService = new IngredientService(ingredientRepository, new IngredientMapperImpl());

        final var ingredient1 = new Ingredient();
        ingredient1.setId(1L);
        ingredient1.setName("PEPPER");

        final var ingredient2 = new Ingredient();
        ingredient2.setId(2L);
        ingredient2.setName("SALT");

        final var ingredient3 = new Ingredient();
        ingredient3.setId(3L);
        ingredient3.setName("CHICKEN");

        ingredientEntities.add(ingredient1);
        ingredientEntities.add(ingredient2);
        ingredientEntities.add(ingredient3);

    }

    @Test
    void findAllTest() {

        // Arrange
        final var ingredientDTO1 = IngredientDTO.builder()
                .id(1L)
                .name("PEPPER")
                .build();

        final var ingredientDTO2 = IngredientDTO.builder()
                .id(2L)
                .name("SALT")
                .build();

        final var ingredientDTO3 = IngredientDTO.builder()
                .id(3L)
                .name("CHICKEN")
                .build();

        final Page<Ingredient> page = new PageImpl<>(ingredientEntities);

        when(ingredientRepository.findAll(any(Pageable.class))).thenReturn(page);

        final var ingredientDTOs = new ArrayList<IngredientDTO>();
        ingredientDTOs.add(ingredientDTO1);
        ingredientDTOs.add(ingredientDTO2);
        ingredientDTOs.add(ingredientDTO3);

        // Act
        final var resultSet = ingredientService.findAll(PageRequest.of(0, 20));

        // Assert
        assertEquals(ingredientDTOs, resultSet);
    }

    @Test
    void findByIdTest() {

        // Arrange
        final var ingredientDTO1 = IngredientDTO.builder()
                .id(1L)
                .name("PEPPER")
                .build();

        when(ingredientRepository.findById(anyLong())).thenReturn(Optional.of(ingredientEntities.get(0)));

        // Act
        final var result = ingredientService.findById(1L);

        // Assert
        assertEquals(ingredientDTO1, result);
    }

    @Test
    void updateTest() {

        // Arrange
        final var updateToDTO = IngredientDTO.builder()
                .id(1L)
                .name("PORK")
                .build();

        final var savedEntity = new Ingredient();
        savedEntity.setId(1L);
        savedEntity.setName("PORK");

        final var expectedDTO = IngredientDTO.builder()
                .id(1L)
                .name("pork")
                .build();

        when(ingredientRepository.findById(anyLong())).thenReturn(Optional.of(ingredientEntities.get(0))); // Return `PEPPER`
        when(ingredientRepository.save(any(Ingredient.class))).thenReturn(savedEntity);

        // Act
        final var result = ingredientService.update(1L, updateToDTO);

        // Assert
        assertEquals(expectedDTO, result);
    }

    @Test
    void saveTest() {

        // Arrange
        final var savedEntity = new Ingredient();
        savedEntity.setId(1L);
        savedEntity.setName("PORK");

        final var saveDTO = IngredientDTO.builder()
                .name("PORK")
                .build();

        final var expectedDTO = IngredientDTO.builder()
                .id(1L)
                .name("pork")
                .build();

        when(ingredientRepository.save(any(Ingredient.class))).thenReturn(savedEntity);

        // Act
        final var result = ingredientService.save(saveDTO);

        // Assert
        assertEquals(expectedDTO, result);
    }

}
