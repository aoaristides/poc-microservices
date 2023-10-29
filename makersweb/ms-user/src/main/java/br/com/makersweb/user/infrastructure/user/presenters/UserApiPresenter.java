package br.com.makersweb.user.infrastructure.user.presenters;

import br.com.makersweb.user.application.user.retrieve.get.UserOutput;
import br.com.makersweb.user.application.user.retrieve.list.UserListOutput;
import br.com.makersweb.user.infrastructure.user.models.UserListResponse;
import br.com.makersweb.user.infrastructure.user.models.UserResponse;

/**
 * @author aaristides
 */
public interface UserApiPresenter {

    static UserResponse present(final UserOutput output) {
        return new UserResponse(
                output.id().getValue(),
                output.name(),
                output.document(),
                output.mail(),
                output.birthDate(),
                output.phoneNumber(),
                output.active(),
                output.createdAt(),
                output.updatedAt(),
                output.deletedAt()
        );
    }

    static UserListResponse present(final UserListOutput output) {
        return new UserListResponse(
                output.id(),
                output.name(),
                output.document(),
                output.mail(),
                output.birthDate(),
                output.phoneNumber(),
                output.isActive(),
                output.createdAt(),
                output.updatedAt(),
                output.deletedAt()
        );
    }

}
