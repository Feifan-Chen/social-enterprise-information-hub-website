package com.feifanchen.thirdyearproject.dao;

import com.feifanchen.thirdyearproject.entities.Tedtalk;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Transactional
public class TedtalkServiceImpl implements TedtalkService {

    private final TedtalkRepository tedtalkRepository;
    @Autowired
    public TedtalkServiceImpl(TedtalkRepository tedtalkRepository) {
        this.tedtalkRepository = tedtalkRepository;
    }

    @Override
    public long count() {
        return tedtalkRepository.count();
    }

    @Override
    public Iterable<Tedtalk> findAll() {
        return tedtalkRepository.findAll();
    }

    @Override
    public Tedtalk findOne(long id) {
        return tedtalkRepository.findById(id).orElse(null);
    }

    @Override
    public Tedtalk save(Tedtalk tedtalk) {
        return tedtalkRepository.save(tedtalk);
    }

    @Override
    public void deleteById(long id) {
        tedtalkRepository.deleteById(id);
    }

    public Iterable<Tedtalk> findAllLatest() {
        return tedtalkRepository.findAll(Sort.by(Sort.Direction.DESC,"time"));
    }
}
