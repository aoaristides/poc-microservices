package br.com.makersweb.user.infrastructure.configuration.usecases;

import br.com.makersweb.user.application.user.create.CreateUserUseCase;
import br.com.makersweb.user.application.user.create.DefaultCreateUserUseCase;
import br.com.makersweb.user.application.user.delete.DefaultDeleteUserUseCase;
import br.com.makersweb.user.application.user.delete.DeleteUserUseCase;
import br.com.makersweb.user.application.user.retrieve.get.DefaultGetUserByIdUseCase;
import br.com.makersweb.user.application.user.retrieve.get.GetUserByIdUseCase;
import br.com.makersweb.user.application.user.retrieve.list.DefaultListUserUseCase;
import br.com.makersweb.user.application.user.retrieve.list.ListUserUseCase;
import br.com.makersweb.user.application.user.update.DefaultUpdateUserUseCase;
import br.com.makersweb.user.application.user.update.UpdateUserUseCase;
import br.com.makersweb.user.domain.address.AddressGateway;
import br.com.makersweb.user.domain.user.UserGateway;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Objects;

/**
 * @author aaristides
 */
@Configuration
public class UserUseCaseConfig {

    private final UserGateway userGateway;
    private final AddressGateway addressGateway;

    public UserUseCaseConfig(final UserGateway userGateway, final AddressGateway addressGateway) {
        this.userGateway = Objects.requireNonNull(userGateway);
        this.addressGateway = Objects.requireNonNull(addressGateway);
    }

    @Bean
    public CreateUserUseCase createUserUseCase() {
        return new DefaultCreateUserUseCase(userGateway, addressGateway);
    }

    @Bean
    public DeleteUserUseCase deleteUserUseCase() {
        return new DefaultDeleteUserUseCase(userGateway);
    }

    @Bean
    public GetUserByIdUseCase getUserByIdUseCase() {
        return new DefaultGetUserByIdUseCase(userGateway);
    }

    @Bean
    public ListUserUseCase listUserUseCase() {
        return new DefaultListUserUseCase(userGateway);
    }

    @Bean
    public UpdateUserUseCase updateUserUseCase() {
        return new DefaultUpdateUserUseCase(userGateway, addressGateway);
    }
}
