package com.ms.user.services;

import com.ms.user.entities.UserEntity;
import com.ms.user.producers.UserProducer;
import com.ms.user.repositories.UserRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private  final UserProducer userProducer;

    @Transactional
    public UserEntity saveUser(UserEntity userEntity){
        Optional<UserEntity> userExists = userRepository.findByEmail(userEntity.getEmail());
        if(userExists.isEmpty()){
            userEntity =  userRepository.save(userEntity);
            userProducer.publishMessageEmail(userEntity);
            return userEntity;
        }else {
            throw new Error("Usuário já existe");
        }

    }
}
