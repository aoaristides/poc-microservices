package br.com.makersweb.user.domain.user;

import br.com.makersweb.user.domain.pagination.Pagination;
import br.com.makersweb.user.domain.pagination.SearchQuery;

import java.util.List;
import java.util.Optional;

/**
 * @author aaristides
 */
public interface UserGateway {

    User create(User aUser);

    void deleteById(UserID anId);

    Optional<User> findById(UserID anId);

    User update(User aUser);

    Pagination<User> findAll(SearchQuery aQuery);

    List<UserID> existsByIds(Iterable<UserID> ids);

}
