package br.com.makersweb.user.infrastructure.user.models;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.time.LocalDate;
import java.util.List;

/**
 * @author aaristides
 * @param name
 * @param document
 * @param mail
 * @param birthDate
 * @param phoneNumber
 * @param addresses
 * @param active
 */
public record UpdateUserRequest(
        @JsonProperty("name") String name,
        @JsonProperty("document") String document,
        @JsonProperty("mail") String mail,
        @JsonProperty("birth_date") LocalDate birthDate,
        @JsonProperty("phone_number") String phoneNumber,
        @JsonProperty("addresses") List<String> addresses,
        @JsonProperty("is_active") Boolean active
) {
}
