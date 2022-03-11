package com.martikan.restaurantapi.domain.user;

import com.martikan.restaurantapi.domain.AbstractEntity;
import com.martikan.restaurantapi.domain.role.Role;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.springframework.data.annotation.LastModifiedDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

/**
 * Entity for `users` table.
 */
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Entity
@Table(name = "users")
public class User extends AbstractEntity {

    private static final long serialVersionUID = 235576159418714394L;

    @NotBlank
    @Size(max = 255)
    @Email
    @Column
    private String email;

    @ToString.Exclude
    @NotBlank
    @Size(max = 66)
    @Column
    private String password;

    @NotBlank
    @Size(max = 100)
    @Column
    private String firstName;

    @NotBlank
    @Size(max = 100)
    @Column
    private String lastName;

    @Size(max = 100)
    @Column
    private String phone;

    @Column
    private LocalDateTime createdAt;

    @LastModifiedDate
    @Column
    private LocalDateTime updatedAt;

    @Column
    private LocalDateTime lastLoginAt;

    @ToString.Exclude
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "users_roles",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id")
    )
    private Set<Role> roles = new HashSet<>();

}
