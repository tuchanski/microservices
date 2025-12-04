package dev.tuchanski.ms.email.consumer;

import dev.tuchanski.ms.email.dto.EmailDTO;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

@Component
public class EmailConsumer {

    @RabbitListener(queues = "${broker.queue.email.name}")
    public void listenEmailQueue(@Payload EmailDTO dto) {
        System.out.println(dto.emailTo());
    }

}
