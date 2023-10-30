package br.com.makersweb.user.infrastructure.api.controllers;

import br.com.makersweb.user.application.address.create.CreateAddressCommand;
import br.com.makersweb.user.application.address.create.CreateAddressOutput;
import br.com.makersweb.user.application.address.create.CreateAddressUseCase;
import br.com.makersweb.user.domain.validation.handler.Notification;
import br.com.makersweb.user.infrastructure.address.models.CreateAddressRequest;
import br.com.makersweb.user.infrastructure.api.AddressAPI;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;
import java.util.Objects;
import java.util.function.Function;

/**
 * @author aaristides
 */
@RestController
public class AddressController implements AddressAPI {

    private final CreateAddressUseCase createAddressUseCase;

    public AddressController(final CreateAddressUseCase createAddressUseCase) {
        this.createAddressUseCase = Objects.requireNonNull(createAddressUseCase);
    }

    @Override
    public ResponseEntity<?> createAddress(final CreateAddressRequest input) {
        final var aCommand = CreateAddressCommand.with(
                input.street(),
                input.streetNumber(),
                input.city(),
                input.state(),
                input.postalCode(),
                input.complement(),
                input.neighborhood(),
                input.active() != null ? input.active() : true
        );

        final Function<Notification, ResponseEntity<?>> onError = notification ->
                ResponseEntity.unprocessableEntity().body(notification);

        final Function<CreateAddressOutput, ResponseEntity<?>> onSuccess = output ->
                ResponseEntity.created(URI.create("/addresses/" + output.id())).body(output);

        return this.createAddressUseCase.execute(aCommand).fold(onError, onSuccess);
    }
}
