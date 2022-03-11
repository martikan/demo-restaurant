package com.martikan.restaurantapi.controller;

import com.martikan.restaurantapi.aspect.IsAdmin;
import com.martikan.restaurantapi.aspect.IsUser;
import com.martikan.restaurantapi.dto.meal.MealDTO;
import com.martikan.restaurantapi.service.MealService;
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
 * Controller for {@link com.martikan.restaurantapi.domain.meal.Meal}.
 */
@IsUser
@RequiredArgsConstructor
@RestController
@RequestMapping("/meals")
public class MealController {

    private final MealService mealService;

    @GetMapping
    public ResponseEntity<List<MealDTO>> findAll(final Pageable pageable) {

        final var meals = mealService.findAll(pageable);

        if (meals.isEmpty()) {
            return ResponseEntity.noContent().build();
        }

        return ResponseEntity.ok(meals);

    }

    @GetMapping("/{id}")
    public ResponseEntity<MealDTO> findById(@PathVariable("id") final Long id) {
        return ResponseEntity.ok(mealService.findById(id));
    }

    @IsAdmin
    @PutMapping("/{id}")
    public ResponseEntity<MealDTO> update(@PathVariable("id") final Long id, @Valid @RequestBody final MealDTO dto) {
        dto.setId(id);
        return ResponseEntity.ok(mealService.update(id, dto));
    }

    @IsAdmin
    @PostMapping
    public ResponseEntity<MealDTO> save(@Valid @RequestBody final MealDTO dto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(mealService.save(dto));
    }

    @IsAdmin
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteById(@PathVariable("id") Long id) {
        mealService.deleteById(id);
        return ResponseEntity.ok().build();
    }

}
