package com.feifanchen.thirdyearproject.dao;

import com.feifanchen.thirdyearproject.entities.User;

public interface UserService {
    Iterable<User> findAll();

    User findOne(long id);

    User save(User user);

    void deleteById(long id);
}
