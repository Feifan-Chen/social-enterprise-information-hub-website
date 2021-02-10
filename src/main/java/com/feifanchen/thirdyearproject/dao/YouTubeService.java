package com.feifanchen.thirdyearproject.dao;

import com.feifanchen.thirdyearproject.entities.YouTube;

public interface YouTubeService  {
    long count();

    Iterable<YouTube> findAll();

    YouTube findOne(long id);

    YouTube save(YouTube youTube);

    void deleteById(long id);
}
