package br.com.makersweb.user.domain.address;

import br.com.makersweb.user.domain.pagination.Pagination;
import br.com.makersweb.user.domain.pagination.SearchQuery;

import java.util.List;
import java.util.Optional;

/**
 * @author aaristides
 */
public interface AddressGateway {

    Address create(Address aAddress);

    void deleteById(AddressID anId);

    Optional<Address> findById(AddressID anId);

    Address update(Address aAddress);

    Pagination<Address> findAll(SearchQuery aQuery);

    List<AddressID> existsByIds(Iterable<AddressID> ids);

}
