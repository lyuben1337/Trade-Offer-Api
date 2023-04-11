package com.example.tradeofferapi.model.response;

import com.example.tradeofferapi.model.entity.Filter;
import lombok.Builder;

import java.util.List;

@Builder
public record AppFilters(

       List<FilterDTO> filters

) {
}
