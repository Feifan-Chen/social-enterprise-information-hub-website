package com.feifanchen.thirdyearproject.dao;

import com.feifanchen.thirdyearproject.entities.ForumComment;
import org.springframework.data.repository.CrudRepository;

public interface ForumCommentRepository extends CrudRepository<ForumComment, Long> {
}
