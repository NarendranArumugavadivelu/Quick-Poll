package com.apress.quickpoll.repository;

import com.apress.quickpoll.entity.Option;
import org.springframework.data.repository.CrudRepository;

public interface OptionRepository extends CrudRepository<Option, Long> {
}
