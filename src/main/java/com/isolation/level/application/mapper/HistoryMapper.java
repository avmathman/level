package com.isolation.level.application.mapper;

import com.isolation.level.application.model.HistoryModel;
import com.isolation.level.domain.history.HistoryEntity;
import org.mapstruct.Mapper;

/**
 * Mapper for converting HistoryEntity to HistoryModel and vice versa.
 */
@Mapper(componentModel = "spring")
public interface HistoryMapper extends ModelMapper<HistoryEntity, HistoryModel> {
}

