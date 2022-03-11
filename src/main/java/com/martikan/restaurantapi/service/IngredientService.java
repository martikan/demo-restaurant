package com.martikan.restaurantapi.service;

import com.martikan.restaurantapi.aspect.IsAdmin;
import com.martikan.restaurantapi.domain.meal.Ingredient;
import com.martikan.restaurantapi.dto.meal.IngredientDTO;
import com.martikan.restaurantapi.mapper.IngredientMapper;
import com.martikan.restaurantapi.repository.IngredientRepository;
import org.springframework.stereotype.Service;

import java.util.Locale;

/**
 * Service for {@link com.martikan.restaurantapi.domain.meal.Ingredient} entity.
 */
@Service
public class IngredientService extends AbstractService<Ingredient, IngredientDTO> {

    public IngredientService(final IngredientRepository ingredientRepository, final IngredientMapper ingredientMapper) {
        super(ingredientRepository, ingredientMapper);
    }

    @IsAdmin
    @Override
    public IngredientDTO update(Long id, IngredientDTO dto) {
        dto.setName(dto.getName().toLowerCase(Locale.ROOT));
        return super.update(id, dto);
    }

    @IsAdmin
    @Override
    public IngredientDTO save(IngredientDTO dto) {
        dto.setName(dto.getName().toLowerCase(Locale.ROOT));
        return super.save(dto);
    }

}
