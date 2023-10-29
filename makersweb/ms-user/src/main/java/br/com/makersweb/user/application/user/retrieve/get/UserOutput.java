package br.com.makersweb.user.application.user.retrieve.get;

import br.com.makersweb.user.domain.address.AddressID;
import br.com.makersweb.user.domain.user.User;
import br.com.makersweb.user.domain.user.UserID;

import java.time.Instant;
import java.time.LocalDate;
import java.util.List;

/**
 * @param id
 * @param name
 * @param document
 * @param mail
 * @param addresses
 * @param birthDate
 * @param phoneNumber
 * @param active
 * @param createdAt
 * @param updatedAt
 * @param deletedAt
 * @author aaristides
 */
public record UserOutput(
        UserID id,
        String name,
        String document,
        String mail,
        List<String> addresses,
        LocalDate birthDate,
        String phoneNumber,
        boolean active,
        Instant createdAt,
        Instant updatedAt,
        Instant deletedAt
) {

    public static UserOutput from(final User aUser) {
        return new UserOutput(
                aUser.getId(),
                aUser.getName(),
                aUser.getDocument(),
                aUser.getMail(),
                aUser.getAddresses().stream()
                        .map(AddressID::getValue)
                        .toList(),
                aUser.getBirthDate(),
                aUser.getPhoneNumber(),
                aUser.isActive(),
                aUser.getCreatedAt(),
                aUser.getUpdatedAt(),
                aUser.getDeletedAt()
        );
    }

}
