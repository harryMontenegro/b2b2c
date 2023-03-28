package com.bancopichincha.b2b2c.service;

import com.bancopichincha.b2b2c.service.dto.PaginableDTO;

import java.util.List;
import java.util.Optional;

public interface CrudService<T, I> {
    T create(T t);
    List<T> list(PaginableDTO pageable);
    void delete(I id);
    T update(T t);
    Optional<T> findOneById(I id);
}
