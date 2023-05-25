package com.apress.quickpoll.vo;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;


public record OptionVO(@JsonProperty("id") Long id, @JsonProperty("value") String value) implements Serializable {

    public OptionVO(Long id, String value) {
        this.id = id;
        this.value = value;
    }

    @Override
    public Long id() {
        return id;
    }

    @Override
    public String value() {
        return value;
    }
}
