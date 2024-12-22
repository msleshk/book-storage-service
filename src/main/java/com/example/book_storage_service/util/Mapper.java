package com.example.book_storage_service.util;

public interface Mapper<E, D> {
    E toEntity(D dto);

    D toDTO(E entity);
}
