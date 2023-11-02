package br.com.makersweb.user.infrastructure.producers.user.impl;

import br.com.makersweb.user.infrastructure.producers.user.UserProducer;
import br.com.makersweb.user.infrastructure.producers.user.models.CreateUserRequest;
import br.com.makersweb.user.infrastructure.producers.user.models.UserMailSender;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * @author aaristides
 */
@Component
@Slf4j
public class DefaultUserProducer implements UserProducer {

    private final RabbitTemplate rabbitTemplate;

    @Value("${broker.queue.mail.name}")
    private String routingKey;

    public DefaultUserProducer(final RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    @Override
    public void sendMessage(final CreateUserRequest input) {
        log.info("Init method sendMessage by create user - {}", input.userId());
        final var subject = "Cadastro realizado com sucesso!";
        final var message = """
                Olá %s, seja bem vindo(a)! \nAgradecemos o seu cadastro.\n
                O seu documento é %s e o status do seu cadastro está como: %s.
                """.formatted(input.name(), input.document(), input.status());
        final var userMailSender = UserMailSender.with(
                input.userId(),
                input.mail(),
                subject,
                message
        );

        rabbitTemplate.convertAndSend("", routingKey, userMailSender);
        log.info("Message send success.");
    }
}
