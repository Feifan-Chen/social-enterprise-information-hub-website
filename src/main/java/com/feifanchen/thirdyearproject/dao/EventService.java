package com.feifanchen.thirdyearproject.dao;

import com.feifanchen.thirdyearproject.entities.Event;

import java.util.Optional;

public interface EventService {
    long count();

    Iterable<Event> findAll();

    Optional<Event> findById(long id);

    void deleteById (long id);

    Event save (Event event);
}
