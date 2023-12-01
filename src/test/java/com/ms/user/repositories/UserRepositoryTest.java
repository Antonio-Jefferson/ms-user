package com.ms.user.repositories;

import com.ms.user.dtos.UserDTO;
import com.ms.user.entities.UserEntity;
import jakarta.persistence.EntityManager;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.test.context.ActiveProfiles;
import static org.assertj.core.api.Assertions.assertThat;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@ActiveProfiles("test")
@DisplayName("Tests for User Repository")
@ComponentScan(basePackages = "com.ms.user.repositories")
class UserRepositoryTest {

    @Autowired
    UserRepository userRepository;
    @Autowired
    EntityManager entityManager;
    @Test
    @DisplayName("Should get User successfully from DB")
    void findByEmailSuccess() {
        String email = "antjeff@gmail.com";
        UserDTO data = new UserDTO("Antonio Jefferson", email, "12345");
        this.createUser(data);

        Optional<UserEntity> resultUser = this.userRepository.findByEmail(email);

       assertThat(resultUser.isPresent()).isTrue();
    }
    @Test
    @DisplayName("Should not get User from DB when user not exists")
    void findByEmailError() {
        String email = "antjeff@gmail.com";
        Optional<UserEntity> resultUser = this.userRepository.findByEmail(email);

        assertThat(resultUser.isEmpty()).isTrue();
    }


    private void createUser(UserDTO data){
        UserEntity newUser = new UserEntity();
        newUser.setName(data.getName());
        newUser.setPassword(data.getPassword());
        newUser.setEmail(data.getEmail());
        this.entityManager.persist(newUser);
    }
}
