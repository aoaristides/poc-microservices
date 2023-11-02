package br.com.makersweb.user.infrastructure.producers.user.models;

/**
 * @author aaristides
 * @param userId
 * @param mailTo
 * @param subject
 * @param message
 */
public record UserMailSender(
        String userId,
        String mailTo,
        String subject,
        String message
) {

    public static UserMailSender with(final String userId, final String mailTo, final String subject, final String message) {
        return new UserMailSender(userId, mailTo, subject, message);
    }

}
