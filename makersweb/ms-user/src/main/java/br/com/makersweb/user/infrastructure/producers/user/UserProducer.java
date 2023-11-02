package br.com.makersweb.user.infrastructure.producers.user;

import br.com.makersweb.user.infrastructure.producers.user.models.CreateUserRequest;

/**
 * @author aaristides
 */
public interface UserProducer {

    void sendMessage(final CreateUserRequest input);

}
