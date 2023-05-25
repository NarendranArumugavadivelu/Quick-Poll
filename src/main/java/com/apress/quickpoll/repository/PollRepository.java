package com.apress.quickpoll.repository;

import com.apress.quickpoll.entity.Poll;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PollRepository extends JpaRepository<Poll, Long> {
}
