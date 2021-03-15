package com.feifanchen.thirdyearproject;

import com.feifanchen.thirdyearproject.dao.UserRepository;
import com.feifanchen.thirdyearproject.entities.User;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.AutoConfigureDataJpa;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.annotation.Rollback;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Rollback(value = false)
public class UserRepositoryTest {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private TestEntityManager entityManager;

    @Test
    public void testNewUser(){
        User user = new User();
        user.setUser_name("Feifan");
        user.setPassword("123456");

        User savedUser = userRepository.save(user);

        User existUser = entityManager.find(User.class, savedUser.getUser_id());

        assertThat(existUser.getUser_name()).isEqualTo(user.getUser_name());
    }

}
