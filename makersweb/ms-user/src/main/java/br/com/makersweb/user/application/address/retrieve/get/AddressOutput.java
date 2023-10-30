package br.com.makersweb.user.application.address.retrieve.get;

import br.com.makersweb.user.domain.address.Address;
import br.com.makersweb.user.domain.address.AddressID;

import java.time.Instant;

/**
 * @param id
 * @param street
 * @param streetNumber
 * @param city
 * @param state
 * @param postalCode
 * @param complement
 * @param neighborhood
 * @param isActive
 * @param createdAt
 * @param updatedAt
 * @param deletedAt
 * @author aaristides
 */
public record AddressOutput(
        AddressID id,
        String street,
        String streetNumber,
        String city,
        String state,
        String postalCode,
        String complement,
        String neighborhood,
        boolean isActive,
        Instant createdAt,
        Instant updatedAt,
        Instant deletedAt
) {

    public static AddressOutput from(final Address aAddress) {
        return new AddressOutput(
                aAddress.getId(),
                aAddress.getStreet(),
                aAddress.getStreetNumber(),
                aAddress.getCity(),
                aAddress.getState(),
                aAddress.getPostalCode(),
                aAddress.getComplement(),
                aAddress.getNeighborhood(),
                aAddress.isActive(),
                aAddress.getCreatedAt(),
                aAddress.getUpdatedAt(),
                aAddress.getDeletedAt()
        );
    }

}
