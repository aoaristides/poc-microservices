package br.com.makersweb.user.infrastructure.address.persistence;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

/**
 * @author aaristides
 */
public interface AddressRepository extends JpaRepository<AddressJpaEntity, String> {

    Page<AddressJpaEntity> findAll(Specification<AddressJpaEntity> whereClause, Pageable page);

    @Query(value = "select a.id from Address a where a.id in :ids")
    List<String> existsByIds(@Param("ids") List<String> ids);

}
