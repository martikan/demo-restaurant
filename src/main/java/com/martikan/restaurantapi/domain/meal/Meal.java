package com.martikan.restaurantapi.domain.meal;

import com.martikan.restaurantapi.domain.AbstractEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OrderBy;
import javax.persistence.Table;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.List;

/**
 * Entity for `meals` table.
 */
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Entity
@Table(name = "meals")
public class Meal extends AbstractEntity {

    private static final long serialVersionUID = 4881148640846218517L;

    @NotBlank
    @Size(max = 255)
    @Column
    private String name;

    @Min(0)
    @Column
    private Float price;

    /**
     * Can be rated between 0 and 3.
     * 0 = It's not spicy at all.
     * 3 = HOT
     */
    @Min(0)
    @Max(3)
    @Column
    private Integer spicy = 0;

    @Column
    private boolean vegan = false;

    @Column
    private boolean glutenFree = false;

    @NotNull
    @Min(0)
    @Column
    private Integer kcal;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "meals_ingredients",
            joinColumns = @JoinColumn(name = "meals_id"),
            inverseJoinColumns = @JoinColumn(name = "ingredients_id")
    )
    @OrderBy("name")
    private List<Ingredient> ingredients = new ArrayList<>();

}
