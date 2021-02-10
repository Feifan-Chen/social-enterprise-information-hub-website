package com.feifanchen.thirdyearproject.dao;

import com.feifanchen.thirdyearproject.entities.Podcast;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Transactional
public class PodcastServiceImpl implements PodcastService {

    private final static Logger log = LoggerFactory.getLogger(PodcastServiceImpl.class);

    private final PodcastRepository podcastRepository;
    @Autowired
    public PodcastServiceImpl(PodcastRepository podcastRepository) {
        this.podcastRepository = podcastRepository;
    }

    @Override
    public long count() {
        return podcastRepository.count();
    }

    @Override
    public Iterable<Podcast> findAll() {
        return podcastRepository.findAll();
    }

    @Override
    public Podcast findOne(long id) {
        return podcastRepository.findById(id).orElse(null);
    }

    @Override
    public Podcast save(Podcast podcast) {
        return podcastRepository.save(podcast);
    }

    @Override
    public void deleteById(long id) {
        podcastRepository.deleteById(id);
    }
}
