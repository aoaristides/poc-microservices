package br.com.makersweb.user.domain.user;

import br.com.makersweb.user.domain.AggregateRoot;
import br.com.makersweb.user.domain.address.Address;
import br.com.makersweb.user.domain.validation.ValidationHandler;

import java.time.Instant;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 * @author aaristides
 */
public class User extends AggregateRoot<UserID> {

    private String name;
    private String document;
    private String mail;
    private List<Address> addresses;
    private LocalDate birthDate;
    private String phoneNumber;
    private boolean active;
    private Instant createdAt;
    private Instant updatedAt;
    private Instant deletedAt;

    private User(
            final UserID anId,
            final String name,
            final String document,
            final String mail,
            final List<Address> addresses,
            final LocalDate birthDate,
            final String phoneNumber,
            final boolean active,
            final Instant createdAt,
            final Instant updatedAt,
            final Instant deletedAt
    ) {
        super(anId);
        this.name = name;
        this.document = document;
        this.mail = mail;
        this.addresses = addresses;
        this.birthDate = birthDate;
        this.phoneNumber = phoneNumber;
        this.active = active;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.deletedAt = deletedAt;
    }

    public static User newUser(
            final String name,
            final String document,
            final String mail,
            final LocalDate birthDate,
            final String phoneNumber,
            final boolean active
    ) {
        final var anId = UserID.unique();
        final var now = Instant.now();
        final var deletedAt = active ? null : now;
        return new User(anId, name, document, mail, new ArrayList<>(), birthDate, phoneNumber, active, now, now, deletedAt);
    }

    public static User with(
            final UserID anId,
            final String name,
            final String document,
            final String mail,
            final List<Address> addresses,
            final LocalDate birthDate,
            final String phoneNumber,
            final boolean active,
            final Instant createdAt,
            final Instant updatedAt,
            final Instant deletedAt
    ) {
        return new User(anId, name, document, mail, addresses, birthDate, phoneNumber, active, createdAt, updatedAt, deletedAt);
    }

    public static User with(final User aUser) {
        return with(
                aUser.getId(),
                aUser.getName(),
                aUser.getDocument(),
                aUser.getMail(),
                new ArrayList<>(aUser.getAddresses()),
                aUser.getBirthDate(),
                aUser.getPhoneNumber(),
                aUser.isActive(),
                aUser.getCreatedAt(),
                aUser.getUpdatedAt(),
                aUser.getDeletedAt()
        );
    }

    @Override
    public void validate(final ValidationHandler handler) {
        new UserValidator(this, handler).validate();
    }

    public User activate() {
        this.deletedAt = null;
        this.active = true;
        this.updatedAt = Instant.now();
        return this;
    }

    public User deactivate() {
        if (getDeletedAt() == null) {
            this.deletedAt = Instant.now();
        }

        this.active = false;
        this.updatedAt = Instant.now();
        return this;
    }

    @Override
    public UserID getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getDocument() {
        return document;
    }

    public String getMail() {
        return mail;
    }

    public List<Address> getAddresses() {
        return addresses;
    }

    public LocalDate getBirthDate() {
        return birthDate;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public boolean isActive() {
        return active;
    }

    public Instant getCreatedAt() {
        return createdAt;
    }

    public Instant getUpdatedAt() {
        return updatedAt;
    }

    public Instant getDeletedAt() {
        return deletedAt;
    }
}
