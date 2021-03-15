package com.feifanchen.thirdyearproject.dao;

import com.feifanchen.thirdyearproject.entities.Roles;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface RolesRepository extends CrudRepository<Roles, Long> {
    @Query("select u from Roles u where u.role_name = 'CHANGEMAKER' ")
    Roles findNormal();
}
