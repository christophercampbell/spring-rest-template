package com.perobscurus.api.controllers;

import com.google.common.collect.ImmutableList;
import com.perobscurus.api.messages.Data;
import com.perobscurus.api.messages.ImmutableData;
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
    public Data forId(@PathVariable final int id) {
        return Data.create(id, "foobar", new Date());
    }

    @RequestMapping(
            value = "/list",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    @ResponseBody
    public ResponseEntity<?> list() {
        final Date now = new Date();
        ImmutableList.Builder builder = ImmutableList.<Data>builder()
                .add(ImmutableData.builder().id(42).name("bar").date(now).build())
                .add(ImmutableData.builder().id(666).name("foo").date(now).build())
                .add(ImmutableData.builder().id(127).name("fah").date(now).build());
        return ResponseEntity.ok(builder.build());
    }
}
