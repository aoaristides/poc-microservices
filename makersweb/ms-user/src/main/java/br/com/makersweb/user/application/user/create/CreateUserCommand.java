package br.com.makersweb.user.application.user.create;

import br.com.makersweb.user.domain.address.Address;

import java.time.LocalDate;
import java.util.List;

/**
 * @author aaristides
 * @param name
 * @param document
 * @param mail
 * @param addresses
 * @param birthDate
 * @param phoneNumber
 * @param isActive
 */
public record CreateUserCommand(
        String name,
        String document,
        String mail,
        List<String> addresses,
        LocalDate birthDate,
        String phoneNumber,
        boolean isActive
) {

    public static CreateUserCommand with(
            final String name,
            final String document,
            final String mail,
            final List<String> addresses,
            final LocalDate birthDate,
            final String phoneNumber,
            final Boolean isActive
    ) {
        return new CreateUserCommand(name, document, mail, addresses, birthDate, phoneNumber, isActive != null ? isActive : true);
    }

}
