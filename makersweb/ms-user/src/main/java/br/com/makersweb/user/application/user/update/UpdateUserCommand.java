package br.com.makersweb.user.application.user.update;

import java.time.LocalDate;
import java.util.List;

/**
 * @author aaristides
 * @param id
 * @param name
 * @param document
 * @param mail
 * @param addresses
 * @param birthDate
 * @param phoneNumber
 * @param isActive
 */
public record UpdateUserCommand(
        String id,
        String name,
        String document,
        String mail,
        List<String> addresses,
        LocalDate birthDate,
        String phoneNumber,
        boolean isActive
) {

    public static UpdateUserCommand with(
            final String id,
            final String name,
            final String document,
            final String mail,
            final List<String> addresses,
            final LocalDate birthDate,
            final String phoneNumber,
            final boolean isActive
    ) {
        return new UpdateUserCommand(id, name, document, mail, addresses, birthDate, phoneNumber, isActive);
    }

}
