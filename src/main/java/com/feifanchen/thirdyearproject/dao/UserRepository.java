package com.feifanchen.thirdyearproject.dao;

import com.feifanchen.thirdyearproject.entities.User;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends CrudRepository<User, Long> {
    @Query("select u from User u where u.user_name = ?1")
    User findbyName(String name);
}
