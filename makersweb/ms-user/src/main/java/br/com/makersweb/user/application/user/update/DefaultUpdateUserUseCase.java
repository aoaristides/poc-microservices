package br.com.makersweb.user.application.user.update;

import br.com.makersweb.user.domain.Identifier;
import br.com.makersweb.user.domain.address.AddressGateway;
import br.com.makersweb.user.domain.address.AddressID;
import br.com.makersweb.user.domain.exceptions.DomainException;
import br.com.makersweb.user.domain.exceptions.NotFoundException;
import br.com.makersweb.user.domain.user.User;
import br.com.makersweb.user.domain.user.UserGateway;
import br.com.makersweb.user.domain.user.UserID;
import br.com.makersweb.user.domain.validation.Error;
import br.com.makersweb.user.domain.validation.ValidationHandler;
import br.com.makersweb.user.domain.validation.handler.Notification;
import io.vavr.control.Either;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.function.Supplier;
import java.util.stream.Collectors;

import static io.vavr.API.Left;
import static io.vavr.API.Try;

/**
 * @author aaristides
 */
public class DefaultUpdateUserUseCase extends UpdateUserUseCase {

    private final UserGateway userGateway;
    private final AddressGateway addressGateway;

    public DefaultUpdateUserUseCase(UserGateway userGateway, AddressGateway addressGateway) {
        this.userGateway = userGateway;
        this.addressGateway = addressGateway;
    }

    @Override
    public Either<Notification, UpdateUserOutput> execute(final UpdateUserCommand anIn) {
        final var anId = UserID.from(anIn.id());
        final var name = anIn.name();
        final var document = anIn.document();
        final var mail = anIn.mail();
        final var addresses = toAddressID(anIn.addresses());
        final var birthDate = anIn.birthDate();
        final var phoneNumber = anIn.phoneNumber();
        final var isActive = anIn.isActive();

        final var aUser = this.userGateway.findById(anId)
                .orElseThrow(notFound(anId));

        final var notification = Notification.create();
        notification.append(validateAddress(addresses));
        aUser
                .update(name, document, mail, addresses, birthDate, phoneNumber, isActive)
                .validate(notification);

        return notification.hasError() ? Left(notification) : update(aUser);
    }

    private Either<Notification, UpdateUserOutput> update(final User aUser) {
        return Try(() -> this.userGateway.update(aUser))
                .toEither()
                .bimap(Notification::create, UpdateUserOutput::from);
    }

    private Supplier<DomainException> notFound(final Identifier anId) {
        return () -> NotFoundException.with(User.class, anId);
    }

    private ValidationHandler validateAddress(final List<AddressID> ids) {
        final var notification = Notification.create();
        if (ids == null || ids.isEmpty()) {
            return notification;
        }

        final var retrievedIds = addressGateway.existsByIds(ids);

        if (ids.size() != retrievedIds.size()) {
            final var missingIds = new ArrayList<>(ids);
            missingIds.removeAll(retrievedIds);

            final var missingIdsMessage = missingIds.stream()
                    .map(AddressID::getValue)
                    .collect(Collectors.joining(", "));

            notification.append(new Error("Some categories could not be found: %s".formatted(missingIdsMessage)));
        }

        return notification;
    }

    private List<AddressID> toAddressID(final List<String> addresses) {
        return !addresses.isEmpty() ? addresses.stream()
                .map(AddressID::from)
                .toList() : Collections.emptyList();
    }
}
