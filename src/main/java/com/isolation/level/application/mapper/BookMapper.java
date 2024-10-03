package com.isolation.level.application.mapper;

import com.isolation.level.application.model.BookModel;
import com.isolation.level.domain.book.BookEntity;
import org.mapstruct.Mapper;

/**
 * Mapper for converting BookEntity to BookModel and vice versa.
 */
@Mapper(componentModel = "spring")
public interface BookMapper extends ModelMapper<BookEntity, BookModel> {

}
