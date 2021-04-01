package com.feifanchen.thirdyearproject.dao;

import com.feifanchen.thirdyearproject.entities.Podcast;
import com.feifanchen.thirdyearproject.entities.Tedtalk;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.CrudRepository;

public interface TedtalkRepository extends CrudRepository<Tedtalk, Long> {
    Iterable<Tedtalk> findAll(Sort sort);
}
