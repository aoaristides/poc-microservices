package br.com.makersweb.user.application.user.update;

import br.com.makersweb.user.application.UseCase;
import br.com.makersweb.user.domain.validation.handler.Notification;
import io.vavr.control.Either;

/**
 * @author aaristides
 */
public abstract class UpdateUserUseCase extends UseCase<UpdateUserCommand, Either<Notification, UpdateUserOutput>> {
}
