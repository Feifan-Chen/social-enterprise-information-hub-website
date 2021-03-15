package com.feifanchen.thirdyearproject.dao;

import com.feifanchen.thirdyearproject.entities.ForumComment;
import com.feifanchen.thirdyearproject.entities.ForumPost;

public interface ForumCommentService {
    long count();

    Iterable<ForumComment> findAll();

    ForumComment findOne(long id);

    //Iterable<ForumComment> findByOnePost(long id);

    ForumComment save(ForumComment forumComment);

    void deleteById(long id);

    ForumComment reportComment(long id);
}
