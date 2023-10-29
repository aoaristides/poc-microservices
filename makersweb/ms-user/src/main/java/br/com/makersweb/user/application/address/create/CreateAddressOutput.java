package br.com.makersweb.user.application.address.create;

import br.com.makersweb.user.application.user.create.CreateUserOutput;
import br.com.makersweb.user.domain.address.Address;

/**
 * @author aaristides
 * @param id
 */
public record CreateAddressOutput(
        String id
) {

    public static CreateAddressOutput from(final String anId) {
        return new CreateAddressOutput(anId);
    }

    public static CreateAddressOutput from(final Address aAddress) {
        return from(aAddress.getId().getValue());
    }

}
