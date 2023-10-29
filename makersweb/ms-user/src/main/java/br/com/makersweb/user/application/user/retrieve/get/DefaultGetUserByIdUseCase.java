package br.com.makersweb.user.application.user.retrieve.get;

import br.com.makersweb.user.domain.exceptions.NotFoundException;
import br.com.makersweb.user.domain.user.User;
import br.com.makersweb.user.domain.user.UserGateway;
import br.com.makersweb.user.domain.user.UserID;

import java.util.Objects;

/**
 * @author aaristides
 */
public class DefaultGetUserByIdUseCase extends GetUserByIdUseCase {

    private final UserGateway userGateway;

    public DefaultGetUserByIdUseCase(final UserGateway userGateway) {
        this.userGateway = Objects.requireNonNull(userGateway);
    }

    @Override
    public UserOutput execute(final String anIn) {
        final var aUserId = UserID.from(anIn);
        return this.userGateway.findById(aUserId)
                .map(UserOutput::from)
                .orElseThrow(() -> NotFoundException.with(User.class, aUserId));
    }
}
