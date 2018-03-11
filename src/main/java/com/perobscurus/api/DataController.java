package com.perobscurus.api;

import com.google.common.collect.ImmutableList;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Date;

@Controller
@RequestMapping("/data")
public class DataController {

    @RequestMapping(
            value = "/{id}",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    @ResponseBody
    public DataObjects.Data forId(@PathVariable final int id) {
        return ImmutableData.builder().id(id).name("cracker").build();
    }

    @RequestMapping(
            value = "/list",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    @ResponseBody
    public ResponseEntity<?> list() {
        final Date now = new Date();
        ImmutableList.Builder builder = ImmutableList.<DataObjects.Data>builder()
                .add(DataObjects.Data.create(42, "bar", now))
                .add(DataObjects.Data.create(69, "foo", now))
                .add(DataObjects.Data.create(666, "duh", now));
        return ResponseEntity.ok(builder.build());
    }
}
