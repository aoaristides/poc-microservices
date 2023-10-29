package br.com.makersweb.user.application.user.retrieve.list;

import br.com.makersweb.user.domain.pagination.Pagination;
import br.com.makersweb.user.domain.pagination.SearchQuery;
import br.com.makersweb.user.domain.user.UserGateway;

import java.util.Objects;

/**
 * @author aaristides
 */
public class DefaultListUserUseCase extends ListUserUseCase {

    private final UserGateway userGateway;

    public DefaultListUserUseCase(final UserGateway userGateway) {
        this.userGateway = Objects.requireNonNull(userGateway);
    }

    @Override
    public Pagination<UserListOutput> execute(final SearchQuery aQuery) {
        return this.userGateway.findAll(aQuery)
                .map(UserListOutput::from);
    }

}
