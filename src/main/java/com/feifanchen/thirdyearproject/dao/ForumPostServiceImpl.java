package com.feifanchen.thirdyearproject.dao;

import com.feifanchen.thirdyearproject.entities.ForumComment;
import com.feifanchen.thirdyearproject.entities.ForumPost;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.Collection;

@Service
@Transactional
public class ForumPostServiceImpl implements ForumPostService {
    private final static Logger log = LoggerFactory.getLogger(ForumPostServiceImpl.class);

    public final ForumPostRepository forumPostRepository;
    @Autowired
    public ForumPostServiceImpl(ForumPostRepository forumPostRepository) {
        this.forumPostRepository = forumPostRepository;
    }

    @Override
    public long count() {
        return forumPostRepository.count();
    }

    @Override
    public Iterable<ForumPost> findAll() {
        if(forumPostRepository.findAll(Sort.by(Sort.Direction.DESC,"watch")) == null)
            return null;
        Iterable<ForumPost> all = forumPostRepository.findAll(Sort.by(Sort.Direction.DESC,"watch"));
        ArrayList<ForumPost> res = new ArrayList<>();
        for(ForumPost f : all){
            if(f.getReport() == 0)
                res.add(f);
        }
        return res;
    }

    @Override
    public Iterable<ForumPost> findAllReported() {
        Iterable<ForumPost> com = forumPostRepository.findAll();
        ArrayList<ForumPost> res = new ArrayList<>();
        for(ForumPost c : com){
            if(c.getReport() == 1 )
                res.add(c);
        }
        return res;
    }

    @Override
    public ForumPost findOne(long id) {
        return forumPostRepository.findById(id).orElse(null);
    }

    @Override
    public ForumPost save(ForumPost forumPost) {
        return forumPostRepository.save(forumPost);
    }

    @Override
    public void deleteById(long id) {
        forumPostRepository.deleteById(id);
    }

    @Override
    public ForumPost reportPost(long id) {
        ForumPost reported = findOne(id);
        if(reported == null){return null;}
        reported.setReport(1);
        return reported;
    }
}
