package com.apress.quickpoll.entity;

import jakarta.persistence.*;

@Entity
public class Vote {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "VOTE_ID")
    private final Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "OPTION_ID")
    private final Option option;

    public Vote(Long id, Option option) {
        this.id = id;
        this.option = option;
    }

    public Long getId() {
        return id;
    }

    public Option getOption() {
        return option;
    }

}
