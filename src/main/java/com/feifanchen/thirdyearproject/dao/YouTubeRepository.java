package com.feifanchen.thirdyearproject.dao;

import com.feifanchen.thirdyearproject.entities.YouTube;
import net.bytebuddy.TypeCache;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.CrudRepository;

public interface YouTubeRepository extends CrudRepository<YouTube, Long> {
    Iterable<YouTube> findAll(Sort sort);
}
