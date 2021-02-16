package com.feifanchen.thirdyearproject.dao;

import com.feifanchen.thirdyearproject.entities.Event;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Optional;

@Service
@Transactional
public class EventServiceImpl implements EventService {

    private final static Logger log = LoggerFactory.getLogger(PodcastServiceImpl.class);

    public final EventRepository eventRepository;
    @Autowired
    public EventServiceImpl(EventRepository eventRepository) {
        this.eventRepository = eventRepository;
    }

    @Override
    public long count() {
        return eventRepository.count();
    }

    @Override
    public Iterable<Event> findAll() {
        return eventRepository.findAll();
    }

    @Override
    public Optional<Event> findById(long id) {
        return eventRepository.findById(id);
    }

    @Override
    public void deleteById(long id) {
        eventRepository.deleteById(id);
    }

    @Override
    public Event save(Event event) {
        return eventRepository.save(event);
    }
}
