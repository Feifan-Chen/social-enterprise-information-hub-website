package com.feifanchen.thirdyearproject.dao;

import com.feifanchen.thirdyearproject.entities.Event;
import org.springframework.data.repository.CrudRepository;

public interface EventRepository extends CrudRepository<Event, Long> {
}
