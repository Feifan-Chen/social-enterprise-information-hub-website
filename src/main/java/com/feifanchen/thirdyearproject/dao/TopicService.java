package com.feifanchen.thirdyearproject.dao;

import com.feifanchen.thirdyearproject.entities.Topic;

import java.util.Optional;

public interface TopicService {

    long count();

    Iterable<Topic> findAll();

    Optional<Topic> findById(long id);

    void deleteById(long id);

    Topic save(Topic topic);

}
