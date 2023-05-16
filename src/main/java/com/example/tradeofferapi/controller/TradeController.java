package com.example.tradeofferapi.controller;

import com.example.tradeofferapi.model.request.FilterParamsDTO;
import com.example.tradeofferapi.model.request.FiltersDTO;
import com.example.tradeofferapi.model.response.AppFilters;
import com.example.tradeofferapi.model.response.AppsDTO;
import com.example.tradeofferapi.service.TradeService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@Validated
@AllArgsConstructor
public class TradeController {

    TradeService tradeService;

    @GetMapping("/api/v1/apps/{app-id}/filters")
    AppFilters getAppFilters(@PathVariable(name = "app-id") long appId) {
        return tradeService.getAppFilters(appId);
    }

    @GetMapping("/api/v1/apps")
    AppsDTO getApps() {
        return tradeService.getApps();
    }

    @PostMapping("/api/v1/{app-id}")
    @ResponseStatus(HttpStatus.CREATED)
    void addApp(@PathVariable(name = "app-id") long appId) {
        tradeService.addApp(appId);
    }

    @PostMapping("/api/v1/apps/{app-id}/filters")
    @ResponseStatus(HttpStatus.CREATED)
    void addFilters(@PathVariable(name = "app-id") long appId,
                    @Valid @RequestBody(required = true) FiltersDTO filters
    ) {
        tradeService.addFilters(appId, filters);
    }

    @PostMapping("/api/v1/apps/{app-id}/filters/{filter-name}")
    @ResponseStatus(HttpStatus.CREATED)
    void addFilterParams(@PathVariable(name = "app-id") long appId,
                         @PathVariable(name = "filter-name") String filter,
                         @Valid @RequestBody FilterParamsDTO params
    ) {
        tradeService.addFilterParams(appId, filter, params);
    }

    @DeleteMapping("/api/v1/apps/{app-id}")
    void deleteApp(@PathVariable("app-id") long appId) {
        tradeService.deleteApp(appId);
    }

    @DeleteMapping("/api/v1/filters/{filter-id}")
    void deleteFilter(@PathVariable("filter-id") long filterId) {
        tradeService.deleteFilter(filterId);
    }

    @DeleteMapping("/api/v1/params/{param-id}")
    void deleteParam(@PathVariable("param-id") long paramId) {
        tradeService.deleteParam(paramId);
    }
}
