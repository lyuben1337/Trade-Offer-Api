package com.example.tradeofferapi.model.request;

import lombok.Builder;

import java.util.List;

@Builder
public record FiltersDTO(
        List<FilterDTO> filters
) {
}
