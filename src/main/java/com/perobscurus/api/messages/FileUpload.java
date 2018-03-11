package com.perobscurus.api.messages;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import org.immutables.value.Value;

@JsonSerialize
@Value.Immutable
public interface FileUpload {

    long size();
    String name();

    static FileUpload create(String name, long size) {
        return ImmutableFileUpload.builder()
                .name(name)
                .size(size)
                .build();
    }

}
