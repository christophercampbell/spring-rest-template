package com.perobscurus.api;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import org.immutables.value.Value;

import java.util.Date;

/**
 * A collection of data objects that have Immutable implementations with builders for convenience
 */
@JsonSerialize                      // jackson integration
@Value.Style(                       // Immutable configuration
        visibility = Value.Style.ImplementationVisibility.PUBLIC)
public interface DataObjects {

    @Value.Immutable
    interface FileUpload {
        long size();
        String name();

        static FileUpload create(String name, long size) {
            return ImmutableFileUpload.builder()
                    .name(name)
                    .size(size)
                    .build();
        }
    }

    @Value.Immutable
    interface Data {
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

}
