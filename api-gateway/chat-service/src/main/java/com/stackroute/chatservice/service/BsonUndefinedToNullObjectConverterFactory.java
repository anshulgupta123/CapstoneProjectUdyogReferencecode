package com.stackroute.chatservice.service;

import org.bson.BsonUndefined;
import org.springframework.core.convert.converter.ConverterFactory;
import org.springframework.data.convert.ReadingConverter;


@ReadingConverter
public class BsonUndefinedToNullObjectConverterFactory implements ConverterFactory<BsonUndefined, Object> {
    @Override
    public <T extends Object> org.springframework.core.convert.converter.Converter<BsonUndefined, T> getConverter(Class<T> targetType) {
        return o -> null;
    }
}
