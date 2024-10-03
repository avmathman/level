package com.isolation.level.presentation.mapper.history;

import com.isolation.level.application.model.HistoryModel;
import com.isolation.level.presentation.dto.history.HistoryDto;
import org.mapstruct.Mapper;

import java.util.List;

/**
 * Mapper for converting BookDto to BookModel and vice versa.
 */
@Mapper(componentModel = "spring")
public interface HistoryReadMapper {

    /**
     * Converts model to dto.
     *
     * @param model - The model {@link HistoryModel} object.
     * @return The dto {@link HistoryDto} object.
     */
    HistoryDto modelToDto(HistoryModel model);

    /**
     * Converts dto to model.
     *
     * @param dto - The dto {@link HistoryDto} object.
     * @return The model {@link HistoryModel} object.
     */
    HistoryModel dtoToModel(HistoryDto dto);

    /**
     * Converts models to DTOs.
     *
     * @param models - The list of dto {@link HistoryModel} objects.
     * @return The list of model {@link HistoryDto} objects.
     */
    List<HistoryDto> modelsToDtos(List<HistoryModel> models);

    /**
     * Converts entities to models.
     *
     * @param dtos - The list of dtos {@link HistoryDto} objects.
     * @return The list of models {@link HistoryModel} objects.
     */
    List<HistoryModel> dtosToModels(List<HistoryDto> dtos);
}
