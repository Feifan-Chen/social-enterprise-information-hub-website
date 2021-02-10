package com.feifanchen.thirdyearproject.dao;

import com.feifanchen.thirdyearproject.entities.User;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User, Long> {
}
