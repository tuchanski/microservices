package dev.tuchanski.ms.user.producer;

import dev.tuchanski.ms.user.dto.EmailDTO;
import dev.tuchanski.ms.user.model.User;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class UserProducer {

    private final RabbitTemplate rabbitTemplate;

    @Value("${broker.queue.email.name}")
    private String routingKey;

    public void publishMessageEmail(User user) {

        String text = user.getName() + ", welcome to this amazing microservices implementation. Hope you enjoy your stay :)";

        EmailDTO emailDTO = new EmailDTO(
                user.getId(),
                user.getEmail(),
                "Your account has been created successfully!",
                text
        );

        rabbitTemplate.convertAndSend("", routingKey, emailDTO);

    }

}
