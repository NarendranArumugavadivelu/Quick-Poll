package com.apress.quickpoll.entity;

import jakarta.persistence.*;

@Entity
public class Option {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "OPTION_ID")
    private Long id;

    @Column(name = "VALUE")
    private String value;

    public Option() {

    }

    public Option(Long id, String value) {
        this.id = id;
        this.value = value;
    }

    public Long getId() {
        return id;
    }

    public String getValue() {
        return value;
    }

}
