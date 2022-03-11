package com.martikan.restaurantapi.repository;

import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.data.repository.PagingAndSortingRepository;

/**
 * Base interface for repositories.
 * @param <T> - Entity.
 */
@NoRepositoryBean
public interface AbstractRepository<T> extends PagingAndSortingRepository<T, Long> {
}
