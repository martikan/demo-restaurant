package com.martikan.restaurantapi.dto.meal;

import com.martikan.restaurantapi.domain.meal.Ingredient;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.List;

/**
 * DTO for {@link com.martikan.restaurantapi.domain.meal.Meal} entity.
 */
@Builder
@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class MealDTO implements Serializable {

    private static final long serialVersionUID = 7737348937364022251L;

    private Long id;

    @NotBlank(message = "Name cannot be empty.")
    @Size(max = 255, message = "Name cannot be grater then 255 characters.")
    private String name;

    @Min(value = 0, message = "Price cannot be less then 0")
    private Float price;

    @Min(value = 0, message = "Spicy cannot be less then 0")
    @Max(value = 3, message = "Spicy cannot be grater then 3")
    private Integer spicy;

    private boolean vegan;

    private boolean glutenFree;

    @NotNull(message = "Kcal cannot be null.")
    @Min(value = 0, message = "Kcal cannot be less then 0")
    private Integer kcal;

    @NotNull(message = "Ingredients cannot be null.")
    private List<Ingredient> ingredients;

}
