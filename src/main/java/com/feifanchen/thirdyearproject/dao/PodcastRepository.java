package com.feifanchen.thirdyearproject.dao;

import com.feifanchen.thirdyearproject.entities.Podcast;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.CrudRepository;

public interface PodcastRepository extends CrudRepository<Podcast, Long> {
    Iterable<Podcast> findAll(Sort sort);
}
