package br.com.makersweb.user.domain.user;

import br.com.makersweb.user.domain.validation.Error;
import br.com.makersweb.user.domain.validation.ValidationHandler;
import br.com.makersweb.user.domain.validation.Validator;

/**
 * @author aaristides
 */
public class UserValidator extends Validator {

    private final User user;
    private static final int NAME_MAX_LENGTH = 255;
    private static final int NAME_MIN_LENGTH = 3;

    public UserValidator(final User aUser, final ValidationHandler aHandler) {
        super(aHandler);
        this.user = aUser;
    }

    @Override
    public void validate() {
        this.checkNameConstraints();
    }

    private void checkNameConstraints() {
        final var name = this.user.getName();
        if (name == null) {
            this.validationHandler().append(new Error("'name' should not be null"));
            return;
        }

        if (name.isBlank()) {
            this.validationHandler().append(new Error("'name' should not be empty"));
            return;
        }

        final var length = name.trim().length();
        if (length > NAME_MAX_LENGTH || length < NAME_MIN_LENGTH) {
            this.validationHandler().append(new Error("'name' must be between 3 and 255 characters"));
        }
    }
}
