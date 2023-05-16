package com.example.tradeofferapi.model.response;

import lombok.Builder;

@Builder
public record ParamDTO(
    long id,
    String value
)
{}
