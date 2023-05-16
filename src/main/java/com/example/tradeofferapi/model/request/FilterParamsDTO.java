package com.example.tradeofferapi.model.request;

import jakarta.validation.constraints.NotNull;

import java.util.List;

public record FilterParamsDTO (
        @NotNull
        List<String> filterParams
) {
}
