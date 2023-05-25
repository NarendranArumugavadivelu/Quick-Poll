package com.apress.quickpoll.vo;

import com.apress.quickpoll.entity.Option;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.OrderBy;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;

import java.io.Serializable;
import java.util.Set;

public record PollVO(@JsonProperty("id") Long id, @JsonProperty("question") @NotEmpty(message = "{NotEmpty.poll.question}") String question,
                     @JsonProperty("options")  @OrderBy @Size(min=2, max = 6, message = "{Size.poll.options}") Set<Option> options) implements Serializable {


    public PollVO(Long id, String question, Set<Option> options) {
        this.id = id;
        this.question = question;
        this.options = options;
    }

    @Override
    public Long id() {
        return id;
    }

    @Override
    public String question() {
        return question;
    }

    @Override
    public Set<Option> options() {
        return options;
    }
}
