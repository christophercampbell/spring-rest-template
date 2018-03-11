package com.perobscurus.api.messages;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import org.immutables.value.Value;

import java.util.Date;

@JsonSerialize
@Value.Immutable
public interface Data {

    int id();
    String name();
    Date date();

    static Data create(int id, String name, Date date) {
        return ImmutableData.builder()
                .id(id)
                .name(name)
                .date(date)
                .build();
    }
}
