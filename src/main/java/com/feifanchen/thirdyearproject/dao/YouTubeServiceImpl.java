package com.feifanchen.thirdyearproject.dao;

import com.feifanchen.thirdyearproject.entities.YouTube;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;

@Service
@Transactional
public class YouTubeServiceImpl implements YouTubeService {
    private final static Logger log = LoggerFactory.getLogger(TopicServiceImpl.class);

    private YouTubeRepository youTubeRepository;
    @Autowired
    public YouTubeServiceImpl(YouTubeRepository youTubeRepository) {
        this.youTubeRepository = youTubeRepository;
    }

    @Override
    public long count() {
        return youTubeRepository.count();
    }

    @Override
    public Iterable<YouTube> findAll() {
        return youTubeRepository.findAll();
    }

    @Override
    public Iterable<YouTube> findAllByTopics() {
        return youTubeRepository.findAll();
    }

    @Override
    public Iterable<YouTube> findAllLatest() {
        return youTubeRepository.findAll(Sort.by(Sort.Direction.DESC,"time"));
    }

    @Override
    public YouTube findOne(long id) {
        return youTubeRepository.findById(id).orElse(null);
    }

    @Override
    public YouTube save(YouTube youTube) {
        return youTubeRepository.save(youTube);
    }

    @Override
    public void deleteById(long id) {
        youTubeRepository.deleteById(id);
    }
}
