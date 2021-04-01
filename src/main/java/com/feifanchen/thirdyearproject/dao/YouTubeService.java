package com.feifanchen.thirdyearproject.dao;

import com.feifanchen.thirdyearproject.entities.YouTubeVideo;
import com.google.api.services.youtube.YouTube;

import java.util.List;

public interface YouTubeService  {
    long count();

    Iterable<YouTubeVideo> findAll();

    Iterable<YouTubeVideo> findAllByTopics();

    Iterable<YouTubeVideo> findAllLatest();

    YouTubeVideo findOne(long id);

    YouTubeVideo save(YouTubeVideo youTube);

    void deleteById(long id);

    List<YouTubeVideo> fetchVideosByQuery(String queryTerm, int status);

    String buildVideoUrl(String videoId);

    YouTube getYouTube();
}
