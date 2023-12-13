package com.ms.user.services;

import com.ms.user.entities.UserEntity;
import com.ms.user.exceptions.ConflictException;
import com.ms.user.producers.UserProducer;
import com.ms.user.repositories.UserRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.context.annotation.ComponentScan;

import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
@DisplayName("Tests for User Service")
@ComponentScan(basePackages = "com.ms.user.services")
class UserServiceTest {


    @Mock
    private UserRepository userRepository;

    @Mock
    private UserProducer userProducer;

    @InjectMocks
    private UserService userService;

    @Test
    void testSaveUserComEmailInexistente() {

        UserEntity userEntity = new UserEntity(UUID.randomUUID(), "jeff", "jeff@gmail.com", "123445");

        when(userRepository.findByEmail(anyString())).thenReturn(Optional.empty());
        when(userRepository.save(any(UserEntity.class))).thenReturn(userEntity);

        UserEntity userSalvo = userService.saveUser(userEntity);

        assertNotNull(userSalvo);
        verify(userRepository, times(1)).findByEmail("jeff@gmail.com");
        verify(userRepository, times(1)).save(userEntity);
        verify(userProducer, times(1)).publishMessageEmail(userEntity);
    }
    @Test
    void testSaveUserAlreadyExists() {
        UserEntity userEntity = new UserEntity();
        userEntity.setEmail("existing@example.com");

        when(userRepository.findByEmail(userEntity.getEmail()))
                .thenReturn(Optional.of(userEntity));

        assertThrows(ConflictException.class, () -> userService.saveUser(userEntity));

        verify(userRepository,never()).save(any());
        verify(userProducer, never()).publishMessageEmail(any());
    }

}
