package com.example.tradeofferapi.model.response;

import lombok.Builder;

import java.util.List;

@Builder
public record FilterResponseDTO(
        long id,
        String name,
        String type,
        List<ParamDTO> params
) {
}
