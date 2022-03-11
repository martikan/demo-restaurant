package com.martikan.restaurantapi.service;

import com.martikan.restaurantapi.aspect.IsAdmin;
import com.martikan.restaurantapi.aspect.IsUser;
import com.martikan.restaurantapi.exception.ResourceNotFoundException;
import com.martikan.restaurantapi.mapper.AbstractMapper;
import com.martikan.restaurantapi.repository.AbstractRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Abstract class for services.
 * @param <T> - Entity.
 * @param <D> - DTO.
 */
@IsUser
@Transactional
@RequiredArgsConstructor
public abstract class AbstractService<T, D> {

    private final AbstractRepository<T> repository;

    private final AbstractMapper<T, D> mapper;

    public List<D> findAll(final Pageable pageable) {
        return repository.findAll(pageable)
                .stream()
                .map(mapper::toDTO).collect(Collectors.toList());
    }

    public D findById(final Long id) {
        return repository.findById(id)
                .map(mapper::toDTO)
                .orElseThrow(() -> new ResourceNotFoundException("Entity", "id", id));
    }

    @IsAdmin
    public D update(final Long id, final D dto) {
        return repository.findById(id)
                .map(existing -> {
                    existing = mapper.updateEntity(mapper.toEntity(dto));
                    return mapper.toDTO(repository.save(existing));
                })
                .orElseThrow(() -> new ResourceNotFoundException("Entity", "id", id));
    }

    @IsAdmin
    public D save(final D dto) {
        return mapper.toDTO(repository.save(mapper.toEntity(dto)));
    }

    @IsAdmin
    public void deleteById(final Long id) {
        repository.deleteById(id);
    }

}
