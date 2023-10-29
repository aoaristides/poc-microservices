package br.com.makersweb.user.application.address.create;

import br.com.makersweb.user.application.UseCase;
import br.com.makersweb.user.domain.validation.handler.Notification;
import io.vavr.control.Either;

/**
 * @author aaristides
 */
public abstract class CreateAddressUseCase extends UseCase<CreateAddressCommand, Either<Notification, CreateAddressOutput>> {
}
