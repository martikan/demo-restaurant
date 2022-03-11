package com.martikan.restaurantapi.dto.meal;

import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.io.Serializable;

/**
 * DTO for {@link com.martikan.restaurantapi.domain.meal.Ingredient} entity.
 */
@Builder
@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class IngredientDTO implements Serializable {

    private static final long serialVersionUID = 3120737372436307778L;

    private Long id;

    @NotBlank(message = "Name cannot be empty.")
    @Size(max = 100, message = "Name cannot be grater then 100 characters.")
    private String name;

}
