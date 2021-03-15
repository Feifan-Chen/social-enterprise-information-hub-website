package com.feifanchen.thirdyearproject.dao;

import com.feifanchen.thirdyearproject.entities.ForumPost;

public interface ForumPostService {
    long count();

    Iterable<ForumPost> findAll();

    ForumPost findOne(long id);

    ForumPost save(ForumPost forumPost);

    void deleteById(long id);

    ForumPost reportPost(long id);
}
