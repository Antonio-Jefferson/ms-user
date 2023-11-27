package com.ms.user.entities;

import com.ms.user.dtos.UserDTO;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.io.Serializable;
import java.util.UUID;

@Entity
@Table(name = "TB_USER")
@Data
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
public class UserEntity implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID userId;
    private String name;
    private String email;
    private String password;

    public static UserEntity toEntity(UserDTO user){
        return UserEntity.builder()
                .name(user.getName())
                .email(user.getEmail())
                .password(user.getPassword()).build();
    }
}
