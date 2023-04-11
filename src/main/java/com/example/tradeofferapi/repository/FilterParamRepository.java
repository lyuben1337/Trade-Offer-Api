package com.example.tradeofferapi.repository;

import com.example.tradeofferapi.model.entity.FilterParam;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface FilterParamRepository extends JpaRepository<FilterParam, Long> {
    boolean existsByFilter_App_IdAndFilter_NameIgnoreCaseAndFilterValueIgnoreCase(long id, String name, String filterValue);

}