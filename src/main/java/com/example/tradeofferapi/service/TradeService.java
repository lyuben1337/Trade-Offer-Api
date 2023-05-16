package com.example.tradeofferapi.service;

import com.example.tradeofferapi.model.entity.App;
import com.example.tradeofferapi.model.entity.Filter;
import com.example.tradeofferapi.model.entity.FilterParam;
import com.example.tradeofferapi.model.request.FilterParamsDTO;
import com.example.tradeofferapi.model.request.FiltersDTO;
import com.example.tradeofferapi.model.response.AppFilters;
import com.example.tradeofferapi.model.response.AppsDTO;
import com.example.tradeofferapi.model.response.FilterResponseDTO;
import com.example.tradeofferapi.model.response.ParamDTO;
import com.example.tradeofferapi.repository.AppRepository;
import com.example.tradeofferapi.repository.FilterParamRepository;
import com.example.tradeofferapi.repository.FilterRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.util.stream.Collectors;

@Slf4j
@Service
@Transactional
@AllArgsConstructor
public class TradeService {

    AppRepository appRepository;
    FilterRepository filterRepository;
    FilterParamRepository filterParamRepository;

    public AppFilters getAppFilters(long appId) {
        var app = appRepository.findById(appId).orElseThrow( () ->
                new ResponseStatusException(HttpStatus.NOT_FOUND,
                        "App with ID " + appId + " not found")
        );
        var filters = app.getFilters()
                .stream()
                .map(this::toFilterDTO)
                .collect(Collectors.toList());
        return AppFilters.builder()
                .filters(filters)
                .build();
    }

    private FilterResponseDTO toFilterDTO (Filter filter) {
        return FilterResponseDTO.builder()
                .id(filter.getId())
                .name(filter.getName())
                .type(filter.getType())
                .params(filter.getParams()
                        .stream()
                        .map(param -> new ParamDTO(param.getId(), param.getFilterValue()))
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

    public void addFilters(long appId, FiltersDTO filtersDTO) {
        var app = appRepository.findById(appId).orElseThrow ( () ->
            new ResponseStatusException(HttpStatus.NOT_FOUND,
                "App with id " + appId + " doesn't exists")
        );

        filtersDTO.filters().stream()
                .filter(filter -> filterRepository.existsByApp_IdAndNameIgnoreCase(appId, filter.name()))
                .forEach(filter -> {
            throw new ResponseStatusException(HttpStatus.CONFLICT,
                    "App with id " + appId + " already has filter with name " + filter);
        });

        filtersDTO.filters().forEach(filter -> filterRepository.save(
                Filter.builder()
                        .app(app)
                        .name(filter.name())
                        .type(filter.type())
                        .build()
        ));

        filtersDTO.filters().forEach(filterDTO -> addFilterParams(appId, filterDTO.name(), new FilterParamsDTO(filterDTO.params())));
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

        params.filterParams().stream().filter(param -> !filterParamRepository.existsByFilter_App_IdAndFilter_NameIgnoreCaseAndFilterValueIgnoreCase
                (appId, filter, param)).forEach(param -> filterParamRepository.save(
                FilterParam.builder()
                        .filter(appFilter)
                        .filterValue(param)
                        .build()
        ));

    }

    public void deleteApp(long appId) {
        var app = appRepository.findById(appId).orElseThrow ( () ->
                new ResponseStatusException(HttpStatus.NOT_FOUND,
                        "App with id " + appId + " doesn't exists")
        );
        app.getFilters().forEach(filter -> {
            filterParamRepository.deleteAll(filter.getParams());
        });
        filterRepository.deleteAll(app.getFilters());
        appRepository.delete(app);
    }

    public void deleteFilter(long filterId) {
        var filter = filterRepository.findById(filterId).orElseThrow(() ->
                new ResponseStatusException(HttpStatus.NOT_FOUND,
                        "Filter with id " + filterId + " doesn't exists")
        );
        filterParamRepository.deleteAll(filter.getParams());
        log.error(filter.toString());
        filterRepository.delete(filter);
    }

    public void deleteParam(long paramId) {
        var param = filterParamRepository.findById(paramId).orElseThrow(() ->
                new ResponseStatusException(HttpStatus.NOT_FOUND,
                        "Param with id " + paramId + " doesn't exists")
        );
        filterParamRepository.delete(param);
    }

    public AppsDTO getApps() {
        return new AppsDTO(appRepository.findAll().stream().mapToLong(App::getId).toArray());
    }
}
