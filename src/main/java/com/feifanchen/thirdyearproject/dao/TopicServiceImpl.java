package com.feifanchen.thirdyearproject.dao;

import com.feifanchen.thirdyearproject.entities.Topic;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Optional;


@Service
@Transactional
public class TopicServiceImpl implements TopicService {
    private final static Logger log = LoggerFactory.getLogger(TopicServiceImpl.class);

    private final TopicRepository topicRepository;
    @Autowired
    public TopicServiceImpl(TopicRepository topicRepository) {
        this.topicRepository = topicRepository;
    }

    @Override
    public long count() {
        return topicRepository.count();
    }

    @Override
    public Iterable<Topic> findAll() {
        return topicRepository.findAll();
    }

    @Override
    public Optional<Topic> findById(long id) {
        return topicRepository.findById(id);
    }

    @Override
    public void deleteById(long id) {
        topicRepository.deleteById(id);
    }

    @Override
    public Topic save(Topic topic) {
        return topicRepository.save(topic);
    }
}
