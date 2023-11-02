package br.com.makersweb.user.infrastructure.producers.user.models;

/**
 * @author aaristides
 * @param userId
 * @param name
 * @param document
 * @param mail
 * @param status
 */
public record CreateUserRequest(
        String userId,
        String name,
        String document,
        String mail,
        String status
) {

    public static CreateUserRequest with(final String userId, final String name, final String document, final String mail, final String status) {
        return new CreateUserRequest(userId, name, document, mail, status);
    }

}
