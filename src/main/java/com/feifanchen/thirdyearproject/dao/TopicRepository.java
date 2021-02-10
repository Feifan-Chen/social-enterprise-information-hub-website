package com.feifanchen.thirdyearproject.dao;

import com.feifanchen.thirdyearproject.entities.Topic;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.CrudRepository;


public interface TopicRepository extends CrudRepository<Topic, Long> {
    //Iterable<Topic> findAll();
}
