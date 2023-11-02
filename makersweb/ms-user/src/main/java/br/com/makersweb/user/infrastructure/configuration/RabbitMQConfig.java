package br.com.makersweb.user.infrastructure.configuration;

import br.com.makersweb.user.infrastructure.configuration.json.Json;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author aaristides
 */
@Configuration
public class RabbitMQConfig {

    @Value("${broker.queue.mail.name}")
    private String queue;

    @Bean
    public Queue queue() {
        return new Queue(queue, true);
    }

    @Bean
    public Jackson2JsonMessageConverter messageConverter() {
        return new Jackson2JsonMessageConverter(Json.mapper());
    }

}
