package com.feifanchen.thirdyearproject.dao;

import com.feifanchen.thirdyearproject.entities.Podcast;
import com.feifanchen.thirdyearproject.entities.YouTube;

public interface PodcastService {

    long count();

    Iterable<Podcast> findAll();

    Iterable<Podcast> findAllByTopics();

    Iterable<Podcast> findAllLatest();

    Podcast findOne(long id);

    Podcast save(Podcast podcast);

    void deleteById(long id);
}
