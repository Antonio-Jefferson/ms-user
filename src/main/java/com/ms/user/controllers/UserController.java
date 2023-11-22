package com.ms.user.controllers;

import com.ms.user.dtos.UserDTO;
import com.ms.user.entities.UserEntity;
import com.ms.user.services.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/users")
public class UserController {

    private final UserService userService;
    @PostMapping
    public ResponseEntity<UserEntity> saveUser(@RequestBody @Valid UserDTO userDto){
        UserEntity userEntity = new UserEntity();
        BeanUtils.copyProperties(userDto, userEntity);
        return ResponseEntity.status(HttpStatus.CREATED).body(userService.saveUser(userEntity));
    }
}
