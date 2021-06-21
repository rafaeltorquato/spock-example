package com.torquato.spock.delivery.people.facade.internal.mapper;

import org.bson.types.ObjectId;
import org.mapstruct.Mapper;

import java.util.Optional;

@Mapper(componentModel = "spring")
public interface ObjectIdMapper {

    default String toString(ObjectId objectId) {
        return Optional.ofNullable(objectId)
                .map(ObjectId::toHexString)
                .orElse(null);
    }

}
