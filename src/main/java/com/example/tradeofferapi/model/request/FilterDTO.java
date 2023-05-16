package com.example.tradeofferapi.model.request;

import jakarta.validation.constraints.NotNull;
import lombok.Builder;

import java.util.List;

@Builder
public record FilterDTO(
        @NotNull
        String name,
        @NotNull
        String type,
        @NotNull
        List<String> params
) {
}
