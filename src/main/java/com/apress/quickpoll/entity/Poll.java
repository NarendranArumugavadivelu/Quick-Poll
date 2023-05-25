package com.apress.quickpoll.entity;

import jakarta.persistence.*;
import java.util.Set;

@Entity
public class Poll {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="POLL_ID")
    private Long id;

    @Column(name = "QUESTION")
    private String question;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "POLL_ID")
    private Set<Option> options;

    public Poll() {

    }

    public Poll(Long id, String question, Set<Option> options) {
        this.id = id;
        this.question = question;
        this.options = options;
    }

    public Long getId() {
        return id;
    }

    public String getQuestion() {
        return question;
    }

    public Set<Option> getOptions() {
        return options;
    }

}
