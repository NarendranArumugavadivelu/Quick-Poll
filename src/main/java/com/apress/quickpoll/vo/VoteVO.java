package com.apress.quickpoll.vo;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;

public record VoteVO(@JsonProperty("id") Long id, @JsonProperty("option") OptionVO optionVO) implements Serializable {

    public VoteVO(Long id, OptionVO optionVO) {
        this.id = id;
        this.optionVO = optionVO;
    }

    @Override
    public Long id() {
        return id;
    }

    @Override
    public OptionVO optionVO() {
        return optionVO;
    }
}
