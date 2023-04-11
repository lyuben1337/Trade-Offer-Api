package com.example.tradeofferapi.repository;

import com.example.tradeofferapi.model.entity.Filter;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface FilterRepository extends JpaRepository<Filter, Long> {
    boolean existsByNameIgnoreCase(String name);

    Optional<Filter> findByNameIgnoreCase(String name);

    boolean existsByApp_IdAndNameIgnoreCase(long id, String name);

    Optional<Filter> findByApp_IdAndNameIgnoreCase(long id, String name);





}