package com.feifanchen.thirdyearproject.dao;

import com.feifanchen.thirdyearproject.entities.YouTubeVideo;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.CrudRepository;

public interface YouTubeRepository extends CrudRepository<YouTubeVideo, Long> {
    Iterable<YouTubeVideo> findAll(Sort sort);
}
