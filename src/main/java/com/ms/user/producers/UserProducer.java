package com.ms.user.producers;

import com.ms.user.dtos.EmailDTO;
import com.ms.user.entities.UserEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class UserProducer {
    private final RabbitTemplate rabbitTemplate;

    @Value("${broker.queue.email.name}")
    private String routingKey;

    public void publishMessageEmail(UserEntity userEntity){
        EmailDTO emailDTO;
        emailDTO = new EmailDTO();
        emailDTO.setUserId(userEntity.getUserId());
        emailDTO.setEmailTo(userEntity.getEmail());
        emailDTO.setSubject("Cadastro realizado com sucesso!");
        emailDTO.setText(userEntity.getName() + " Seja bem vindo(a) ao MidArt! \n Agradecemos o seu cadastro em nossa plataforma!");

        rabbitTemplate.convertAndSend("", routingKey, emailDTO);
    }
}
