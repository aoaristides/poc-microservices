package br.com.makersweb.user.infrastructure.api.controllers;

import br.com.makersweb.user.application.user.create.CreateUserCommand;
import br.com.makersweb.user.application.user.create.CreateUserOutput;
import br.com.makersweb.user.application.user.create.CreateUserUseCase;
import br.com.makersweb.user.application.user.delete.DeleteUserUseCase;
import br.com.makersweb.user.application.user.retrieve.get.GetUserByIdUseCase;
import br.com.makersweb.user.application.user.retrieve.list.ListUserUseCase;
import br.com.makersweb.user.application.user.update.UpdateUserCommand;
import br.com.makersweb.user.application.user.update.UpdateUserOutput;
import br.com.makersweb.user.application.user.update.UpdateUserUseCase;
import br.com.makersweb.user.domain.pagination.Pagination;
import br.com.makersweb.user.domain.pagination.SearchQuery;
import br.com.makersweb.user.domain.validation.handler.Notification;
import br.com.makersweb.user.infrastructure.api.UserAPI;
import br.com.makersweb.user.infrastructure.user.models.CreateUserRequest;
import br.com.makersweb.user.infrastructure.user.models.UpdateUserRequest;
import br.com.makersweb.user.infrastructure.user.models.UserListResponse;
import br.com.makersweb.user.infrastructure.user.models.UserResponse;
import br.com.makersweb.user.infrastructure.user.presenters.UserApiPresenter;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;
import java.util.Collections;
import java.util.Objects;
import java.util.function.Function;

/**
 * @author aaristides
 */
@RestController
public class UserController implements UserAPI {

    private final CreateUserUseCase createUserUseCase;
    private final GetUserByIdUseCase getUserByIdUseCase;
    private final UpdateUserUseCase updateUserUseCase;
    private final DeleteUserUseCase deleteUserUseCase;
    private final ListUserUseCase listUserUseCase;

    public UserController(
            final CreateUserUseCase createUserUseCase,
            final GetUserByIdUseCase getUserByIdUseCase,
            final UpdateUserUseCase updateUserUseCase,
            final DeleteUserUseCase deleteUserUseCase,
            final ListUserUseCase listUserUseCase
    ) {
        this.createUserUseCase = Objects.requireNonNull(createUserUseCase);
        this.getUserByIdUseCase = Objects.requireNonNull(getUserByIdUseCase);
        this.updateUserUseCase = Objects.requireNonNull(updateUserUseCase);
        this.deleteUserUseCase = Objects.requireNonNull(deleteUserUseCase);
        this.listUserUseCase = Objects.requireNonNull(listUserUseCase);
    }

    @Override
    public ResponseEntity<?> createUser(final CreateUserRequest input) {
        final var aCommand = CreateUserCommand.with(
                input.name(),
                input.document(),
                input.mail(),
                Collections.emptyList(),
                input.birthDate(),
                input.phoneNumber(),
                input.active() != null ? input.active() : true
        );

        final Function<Notification, ResponseEntity<?>> onError = notification ->
                ResponseEntity.unprocessableEntity().body(notification);

        final Function<CreateUserOutput, ResponseEntity<?>> onSuccess = output ->
                ResponseEntity.created(URI.create("/users/" + output.id())).body(output);

        return this.createUserUseCase.execute(aCommand).fold(onError, onSuccess);
    }

    @Override
    public Pagination<UserListResponse> listUsers(
            final String search,
            final int page,
            final int perPage,
            final String sort,
            final String direction
    ) {
        return this.listUserUseCase.execute(new SearchQuery(page, perPage, search, sort, direction))
                .map(UserApiPresenter::present);
    }

    @Override
    public UserResponse getById(final String id) {
        return UserApiPresenter.present(this.getUserByIdUseCase.execute(id));
    }

    @Override
    public ResponseEntity<?> updateById(final String id, final UpdateUserRequest input) {
        final var aCommand = UpdateUserCommand.with(
                id,
                input.name(),
                input.document(),
                input.mail(),
                null,
                input.birthDate(),
                input.phoneNumber(),
                input.active() != null ? input.active() : true
        );

        final Function<Notification, ResponseEntity<?>> onError = notification ->
                ResponseEntity.unprocessableEntity().body(notification);

        final Function<UpdateUserOutput, ResponseEntity<?>> onSuccess =
                ResponseEntity::ok;

        return this.updateUserUseCase.execute(aCommand).fold(onError, onSuccess);
    }

    @Override
    public void deleteById(final String anId) {
        this.deleteUserUseCase.execute(anId);
    }
}
