package br.com.makersweb.user.application.user.create;

import br.com.makersweb.user.application.UseCase;
import br.com.makersweb.user.domain.validation.handler.Notification;
import io.vavr.control.Either;

/**
 * @author aaristides
 */
public abstract class CreateUserUseCase
        extends UseCase<CreateUserCommand, Either<Notification, CreateUserOutput>> {
}
