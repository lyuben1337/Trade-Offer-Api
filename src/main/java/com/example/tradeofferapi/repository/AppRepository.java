package com.example.tradeofferapi.repository;

import com.example.tradeofferapi.model.entity.App;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AppRepository extends JpaRepository<App, Long> {

    @Override
    Optional<App> findById(Long aLong);
}