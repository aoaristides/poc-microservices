package br.com.makersweb.user.application.user.retrieve.list;

import br.com.makersweb.user.domain.address.AddressID;
import br.com.makersweb.user.domain.user.User;

import java.time.Instant;
import java.time.LocalDate;
import java.util.List;

/**
 * @param name
 * @param document
 * @param mail
 * @param addresses
 * @param birthDate
 * @param phoneNumber
 * @param isActive
 * @param createdAt
 * @param updatedAt
 * @param deletedAt
 * @author aaristides
 */
public record UserListOutput(
        String name,
        String document,
        String mail,
        List<String> addresses,
        LocalDate birthDate,
        String phoneNumber,
        boolean isActive,
        Instant createdAt,
        Instant updatedAt,
        Instant deletedAt
) {

    public static UserListOutput from(final User aUser) {
        return new UserListOutput(
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
