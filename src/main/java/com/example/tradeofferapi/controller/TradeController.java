package com.example.tradeofferapi.controller;

import com.example.tradeofferapi.model.request.FilterParamsDTO;
import com.example.tradeofferapi.model.request.FiltersDTO;
import com.example.tradeofferapi.model.response.AppFilters;
import com.example.tradeofferapi.service.TradeService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@AllArgsConstructor
public class TradeController {

    TradeService tradeService;

    @GetMapping("/api/v1/{app-id}/filters")
    AppFilters getAppFilters(@PathVariable(name = "app-id") long appId) {
        return tradeService.getAppFilters(appId);
    }

    @PostMapping("/api/v1/{app-id}")
    @ResponseStatus(HttpStatus.CREATED)
    void addApp(@PathVariable(name = "app-id") long appId) {
        tradeService.addApp(appId);
    }

    @PostMapping("/api/v1/{app-id}/filters")
    @ResponseStatus(HttpStatus.CREATED)
    void addFilters(@PathVariable(name = "app-id") long appId,
                   @RequestBody(required = true) FiltersDTO filters
                   ) {
        tradeService.addFilters(appId, filters);
    }

    @PostMapping("/api/v1/{app-id}/filters/{filter-name}")
    @ResponseStatus(HttpStatus.CREATED)
    void addFilterParams(@PathVariable(name = "app-id") long appId,
                         @PathVariable(name = "filter-name") String filter,
                         @RequestBody FilterParamsDTO params
                            ) {
        tradeService.addFilterParams(appId, filter, params);
    }

}
