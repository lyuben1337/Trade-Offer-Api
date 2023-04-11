package com.example.tradeofferapi.model.response;

import com.example.tradeofferapi.model.entity.Filter;
import lombok.Builder;

import java.util.List;

@Builder
public record FilterDTO (
    String name,
    List<String> params
) {

}
