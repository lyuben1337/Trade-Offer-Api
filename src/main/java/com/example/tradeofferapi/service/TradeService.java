package com.example.tradeofferapi.service;

import com.example.tradeofferapi.model.entity.App;
import com.example.tradeofferapi.model.entity.Filter;
import com.example.tradeofferapi.model.entity.FilterParam;
import com.example.tradeofferapi.model.request.FilterParamsDTO;
import com.example.tradeofferapi.model.response.AppFilters;
import com.example.tradeofferapi.model.response.FilterDTO;
import com.example.tradeofferapi.repository.AppRepository;
import com.example.tradeofferapi.repository.FilterParamRepository;
import com.example.tradeofferapi.repository.FilterRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.stream.Collectors;

@Slf4j
@Service
@AllArgsConstructor
public class TradeService {

    AppRepository appRepository;
    FilterRepository filterRepository;
    FilterParamRepository filterParamRepository;

    public AppFilters getAppFilters(long appId) {
        var app = appRepository.findById(appId).orElseThrow( () ->
                new ResponseStatusException(HttpStatus.NOT_FOUND)
        );
        var filters = app.getFilters()
                .stream()
                .map(this::toFilterDTO)
                .collect(Collectors.toList());
        return AppFilters.builder()
                .filters(filters)
                .build();
    }

    private FilterDTO toFilterDTO (Filter filter) {
        return FilterDTO.builder()
                .name(filter.getName())
                .params(filter.getParams()
                        .stream()
                        .map(FilterParam::getFilterValue)
                        .toList()
                )
                .build();
    }

    public void addApp(long appId) {
        if(appRepository.existsById(appId)) {
            throw new ResponseStatusException(HttpStatus.CONFLICT,
                    "App with id " + appId + " already exists");
        }

        appRepository.save (
                App.builder()
                        .id(appId)
                        .build()
        );
    }

    public void addFilter(long appId, String filter) {
        var app = appRepository.findById(appId).orElseThrow ( () ->
            new ResponseStatusException(HttpStatus.NOT_FOUND,
                "App with id " + appId + " doesn't exists")
        );

        if(filterRepository.existsByApp_IdAndNameIgnoreCase(appId, filter)) {
            throw new ResponseStatusException(HttpStatus.CONFLICT,
                    "App with id " + appId + " already has filter with name " + filter);
        }

        filterRepository.save (
                Filter.builder()
                        .app(app)
                        .name(filter)
                        .build()
        );
    }

    public void addFilterParams(long appId, String filter, FilterParamsDTO params) {
        var app = appRepository.findById(appId).orElseThrow ( () ->
                new ResponseStatusException(HttpStatus.NOT_FOUND,
                        "App with id " + appId + " doesn't exists")
        );

        var appFilter = filterRepository.findByApp_IdAndNameIgnoreCase(appId, filter).orElseThrow( () ->
                new ResponseStatusException(HttpStatus.NOT_FOUND,
                        "App with id " + appId + " has not filter with name " + filter)
        );

        log.warn("appId = {}, filterName = {}, filterParams = {}", appId, filter, params.filterParams().toString());
        for (String param:
                params.filterParams()) {
            if(!filterParamRepository.existsByFilter_App_IdAndFilter_NameIgnoreCaseAndFilterValueIgnoreCase
                    (appId, filter, param)) {
                filterParamRepository.save(
                        FilterParam.builder()
                                .filter(appFilter)
                                .filterValue(param)
                                .build()
                );
            }
        }

    }
}
