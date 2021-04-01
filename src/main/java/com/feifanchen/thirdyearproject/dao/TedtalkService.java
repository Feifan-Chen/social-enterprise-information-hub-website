package com.feifanchen.thirdyearproject.dao;

import com.feifanchen.thirdyearproject.entities.Tedtalk;

public interface TedtalkService {
    long count();

    Iterable<Tedtalk> findAll();

    Tedtalk findOne(long id);

    Tedtalk save(Tedtalk tedtalk);

    void deleteById(long id);

    Iterable<Tedtalk> findAllLatest();
}
