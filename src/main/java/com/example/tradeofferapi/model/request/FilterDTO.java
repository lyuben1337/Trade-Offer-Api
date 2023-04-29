package com.example.tradeofferapi.model.request;

import lombok.Builder;

@Builder
public record FilterDTO(
        String name,
        String type
) {
}
