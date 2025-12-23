package com.example;

import com.example.model.UserInfo;
import com.example.repository.UserInfoRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.data.jpa.test.autoconfigure.DataJpaTest;
import org.springframework.boot.jdbc.test.autoconfigure.AutoConfigureTestDatabase;
import org.springframework.boot.jpa.test.autoconfigure.TestEntityManager;




@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)// Use the application's defined DataSource
public class UserInfoRepositoryTest {
    @Autowired
    private UserInfoRepository userInfoRepository;

    @Autowired
    private TestEntityManager entityManager;
    @Test
    void whenFindByUsername_thenReturnUser() {
        // given
        UserInfo testUser = new UserInfo("ubaid", "ubaid@galaxy.net");
        entityManager.persist(testUser);
        entityManager.flush(); // Ensure data is saved to the database

        // whenant to confirm that data is really going to the
        UserInfo found = userInfoRepository.findUserInfoByName("ubaid");


        // then
        Assertions.assertThat(found.getName()).isEqualTo(testUser.getName());
    }

}
