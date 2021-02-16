package com.feifanchen.thirdyearproject.dao;

import com.feifanchen.thirdyearproject.entities.Tedtalk;
import com.feifanchen.thirdyearproject.entities.YouTube;

public interface TedtalkService {
    long count();

    Iterable<Tedtalk> findAll();

    Tedtalk findOne(long id);

    Tedtalk save(Tedtalk tedtalk);

    void deleteById(long id);
}
