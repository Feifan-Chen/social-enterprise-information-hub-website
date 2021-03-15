package com.feifanchen.thirdyearproject.dao;

import com.feifanchen.thirdyearproject.entities.ForumComment;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.Optional;

@Service
@Transactional
public class ForumCommentServiceImpl implements ForumCommentService {
    private final static Logger log = LoggerFactory.getLogger(ForumPostServiceImpl.class);

    private  final ForumCommentRepository forumCommentRepository;
    @Autowired
    public ForumCommentServiceImpl(ForumCommentRepository forumCommentRepository) {
        this.forumCommentRepository = forumCommentRepository;
    }

    @Override
    public long count() {
        return forumCommentRepository.count();
    }

    @Override
    public Iterable<ForumComment> findAll() {
        return forumCommentRepository.findAll();
    }

    @Override
    public ForumComment findOne(long id) {
        return forumCommentRepository.findById(id).orElse(null);
    }

//    @Override
//    public Iterable<ForumComment> findByOnePost(long id) {
//        //input id is the post id, find all comments under that id.
//        Iterable<ForumComment> temp =  forumCommentRepository.findAll();
//        ArrayList<ForumComment> res = new ArrayList<ForumComment>();
//        for(ForumComment t : temp){
//            if(t.getPost_id() == id)
//                res.add(t);
//        }
//        return res;
//    }

    @Override
    public ForumComment save(ForumComment forumComment) {
        return forumCommentRepository.save(forumComment);
    }

    @Override
    public void deleteById(long id) {
        forumCommentRepository.deleteById(id);
    }

    @Override
    public ForumComment reportComment(long id) {
        ForumComment reported = findOne(id);
        if(reported == null){return null;}
        reported.setReport(1);
        return reported;
    }
}
