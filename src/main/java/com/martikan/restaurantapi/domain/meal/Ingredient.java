package com.martikan.restaurantapi.domain.meal;

import com.martikan.restaurantapi.domain.AbstractEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

/**
 * Entity for `ingredients` table.
 */
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Entity
@Table(name = "ingredients")
public class Ingredient extends AbstractEntity {

    private static final long serialVersionUID = -8757457838697303870L;

    @NotBlank
    @Size(max = 100)
    @Column
    private String name;

}
