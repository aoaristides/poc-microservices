package br.com.makersweb.user.infrastructure.address.models;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * @author aaristides
 * @param street
 * @param streetNumber
 * @param city
 * @param state
 * @param postalCode
 * @param complement
 * @param neighborhood
 * @param active
 */
public record CreateAddressRequest(
        @JsonProperty("street") String street,
        @JsonProperty("street_number") String streetNumber,
        @JsonProperty("city") String city,
        @JsonProperty("state") String state,
        @JsonProperty("postal_code") String postalCode,
        @JsonProperty("complement")  String complement,
        @JsonProperty("neighborhood") String neighborhood,
        @JsonProperty("is_active") Boolean active
) {
}
