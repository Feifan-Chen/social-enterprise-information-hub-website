package com.feifanchen.thirdyearproject.dao;

import com.feifanchen.thirdyearproject.entities.ForumPost;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.CrudRepository;

public interface ForumPostRepository extends CrudRepository<ForumPost, Long> {
    Iterable<ForumPost> findAll(Sort sort);
}
