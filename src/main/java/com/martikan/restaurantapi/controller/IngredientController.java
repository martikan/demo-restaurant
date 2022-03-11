package com.martikan.restaurantapi.controller;

import com.martikan.restaurantapi.aspect.IsAdmin;
import com.martikan.restaurantapi.aspect.IsUser;
import com.martikan.restaurantapi.dto.meal.IngredientDTO;
import com.martikan.restaurantapi.service.IngredientService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;

/**
 * Controller for {@link com.martikan.restaurantapi.domain.meal.Ingredient}.
 */
@IsUser
@RequiredArgsConstructor
@RestController
@RequestMapping("/ingredients")
public class IngredientController {

    private final IngredientService ingredientService;

    @GetMapping
    public ResponseEntity<List<IngredientDTO>> findAll(final Pageable pageable) {

        final var ingredients = ingredientService.findAll(pageable);

        if (ingredients.isEmpty()) {
            return ResponseEntity.noContent().build();
        }

        return ResponseEntity.ok(ingredients);

    }

    @GetMapping("/{id}")
    public ResponseEntity<IngredientDTO> findById(@PathVariable("id") final Long id) {
        return ResponseEntity.ok(ingredientService.findById(id));
    }

    @IsAdmin
    @PutMapping("/{id}")
    public ResponseEntity<IngredientDTO> update(@PathVariable("id") final Long id, @Valid @RequestBody final IngredientDTO dto) {
        dto.setId(id);
        return ResponseEntity.ok(ingredientService.update(id, dto));
    }

    @IsAdmin
    @PostMapping
    public ResponseEntity<IngredientDTO> save(@Valid @RequestBody final IngredientDTO dto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(ingredientService.save(dto));
    }

    @IsAdmin
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteById(@PathVariable("id") Long id) {
        ingredientService.deleteById(id);
        return ResponseEntity.ok().build();
    }

}
