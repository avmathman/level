package com.isolation.level.presentation.mapper.history;

import com.isolation.level.application.model.HistoryModel;
import com.isolation.level.presentation.dto.history.HistoryCreateDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

/**
 * Mapper for converting BookCreateDto to BookModel.
 */
@Mapper(componentModel = "spring")
public interface HistoryCreateMapper {

    /**
     * Converts entity to model.
     *
     * @param historyCreateDto - The dto{@link HistoryCreateDto} object.
     * @return The model{@link HistoryModel} object.
     */
    @Mapping(target = "id", ignore = true)
    HistoryModel dtoToModel(HistoryCreateDto historyCreateDto);
}
