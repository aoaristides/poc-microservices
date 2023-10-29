package br.com.makersweb.user.application.user.update;

import br.com.makersweb.user.domain.user.User;

/**
 * @author aaristides
 * @param id
 */
public record UpdateUserOutput(
        String id
) {

    public static UpdateUserOutput from(final String anId) {
        return new UpdateUserOutput(anId);
    }

    public static UpdateUserOutput from(final User aUser) {
        return from(aUser.getId().getValue());
    }

}
