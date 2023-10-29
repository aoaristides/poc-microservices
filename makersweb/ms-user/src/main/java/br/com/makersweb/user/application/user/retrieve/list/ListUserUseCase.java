package br.com.makersweb.user.application.user.retrieve.list;

import br.com.makersweb.user.application.UseCase;
import br.com.makersweb.user.domain.pagination.Pagination;
import br.com.makersweb.user.domain.pagination.SearchQuery;

/**
 * @author aaristides
 */
public abstract class ListUserUseCase extends UseCase<SearchQuery, Pagination<UserListOutput>> {
}
