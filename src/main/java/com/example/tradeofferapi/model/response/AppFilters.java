package com.example.tradeofferapi.model.response;

import lombok.Builder;

import java.util.List;

@Builder
public record AppFilters(

       List<FilterResponseDTO> filters

) {
}
