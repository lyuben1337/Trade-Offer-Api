package com.example.tradeofferapi.model.request;

import java.util.List;

public record FilterParamsDTO (
        List<String> filterParams
) {
}
