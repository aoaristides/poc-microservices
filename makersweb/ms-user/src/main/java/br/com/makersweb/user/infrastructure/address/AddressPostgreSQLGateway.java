package br.com.makersweb.user.infrastructure.address;

import br.com.makersweb.user.domain.address.Address;
import br.com.makersweb.user.domain.address.AddressGateway;
import br.com.makersweb.user.domain.address.AddressID;
import br.com.makersweb.user.domain.pagination.Pagination;
import br.com.makersweb.user.domain.pagination.SearchQuery;
import br.com.makersweb.user.infrastructure.address.persistence.AddressJpaEntity;
import br.com.makersweb.user.infrastructure.address.persistence.AddressRepository;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.stream.StreamSupport;

import static br.com.makersweb.user.infrastructure.utils.SpecificationUtils.like;

/**
 * @author aaristides
 */
@Component
public class AddressPostgreSQLGateway implements AddressGateway {

    private final AddressRepository repository;

    public AddressPostgreSQLGateway(final AddressRepository repository) {
        this.repository = repository;
    }

    @Override
    public Address create(final Address aAddress) {
        return save(aAddress);
    }

    @Override
    public void deleteById(AddressID anId) {
        final String anIdValue = anId.getValue();
        if (this.repository.existsById(anIdValue)) {
            this.repository.deleteById(anIdValue);
        }
    }

    @Override
    public Optional<Address> findById(AddressID anId) {
        return this.repository.findById(anId.getValue())
                .map(AddressJpaEntity::toAggregate);
    }

    @Override
    public Address update(Address aAddress) {
        return save(aAddress);
    }

    @Override
    public Pagination<Address> findAll(SearchQuery aQuery) {
        // Paginação
        final var page = PageRequest.of(
                aQuery.page(),
                aQuery.perPage(),
                Sort.by(Sort.Direction.fromString(aQuery.direction()), aQuery.sort())
        );

        // Busca dinamica pelo criterio terms (name ou description)
        final var specifications = Optional.ofNullable(aQuery.terms())
                .filter(str -> !str.isBlank())
                .map(this::assembleSpecification)
                .orElse(null);

        final var pageResult = this.repository.findAll(Specification.where(specifications), page);

        return new Pagination<>(
                pageResult.getNumber(),
                pageResult.getSize(),
                pageResult.getTotalElements(),
                pageResult.map(AddressJpaEntity::toAggregate).toList()
        );
    }

    @Override
    public List<AddressID> existsByIds(Iterable<AddressID> addressIDS) {
        final var ids = StreamSupport.stream(addressIDS.spliterator(), false)
                .map(AddressID::getValue)
                .toList();
        return this.repository.existsByIds(ids).stream()
                .map(AddressID::from)
                .toList();
    }

    private Address save(final Address aAddress) {
        return this.repository.save(AddressJpaEntity.from(aAddress)).toAggregate();
    }

    private Specification<AddressJpaEntity> assembleSpecification(final String str) {
        final Specification<AddressJpaEntity> nameLike = like("street", str);
        final Specification<AddressJpaEntity> descriptionLike = like("postalCode", str);
        return nameLike.or(descriptionLike);
    }
}
