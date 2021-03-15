package com.feifanchen.thirdyearproject.dao;

import com.feifanchen.thirdyearproject.entities.YouTube;

public interface YouTubeService  {
    long count();

    Iterable<YouTube> findAll();

    Iterable<YouTube> findAllByTopics();

    Iterable<YouTube> findAllLatest();

    YouTube findOne(long id);

    YouTube save(YouTube youTube);

    void deleteById(long id);
}
