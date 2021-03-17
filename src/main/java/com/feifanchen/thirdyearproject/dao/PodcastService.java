package com.feifanchen.thirdyearproject.dao;

import com.feifanchen.thirdyearproject.entities.Podcast;

public interface PodcastService {

    long count();

    Iterable<Podcast> findAll();

    Iterable<Podcast> findAllByTopics();

    Iterable<Podcast> findAllLatest();

    Podcast findOne(long id);

    Podcast save(Podcast podcast);

    void deleteById(long id);
}
