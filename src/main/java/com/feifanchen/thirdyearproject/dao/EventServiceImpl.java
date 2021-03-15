package com.feifanchen.thirdyearproject.dao;

import com.feifanchen.thirdyearproject.entities.Event;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
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
        return eventRepository.findAll(Sort.by(Sort.Direction.ASC,"date"));
    }

    @Override
    public Iterable<Event> findAllToday() {
        Iterable<Event> events = findAll();
        ArrayList<Event> ret = new ArrayList<>();

        for (Event e : events) {
            if (e.happenToday())
                ret.add(e);
        }

        return ret;
    }

    @Override
    public Iterable<Event> findAllUpcoming() {
        Iterable<Event> events = findAll();
        ArrayList<Event> ret = new ArrayList<>();

        for (Event e : events) {
            if (e.happenUpcoming())
                ret.add(e);
        }

        return ret;
    }

    @Override
    public Iterable<Event> findList() {
        ArrayList<Event> ret = new ArrayList<>();
        for(Event e : findAllToday()){
            ret.add(e);
        }
        for(Event e : findAllUpcoming()){
            ret.add(e);
        }
        return ret;
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
