package br.com.makersweb.user.infrastructure.address.persistence;

import br.com.makersweb.user.domain.address.Address;
import br.com.makersweb.user.domain.address.AddressID;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import java.io.Serializable;
import java.time.Instant;

/**
 * @author aaristides
 */
@Entity(name = "Address")
@Table(name = "TB_ADDRESS")
public class AddressJpaEntity implements Serializable {

    @Id
    @Column(name = "id", nullable = false)
    private String id;

    @Column(name = "street", nullable = false)
    private String street;

    @Column(name = "street_number", nullable = false)
    private String streetNumber;

    @Column(name = "city", nullable = false)
    private String city;

    @Column(name = "state", nullable = false, length = 2)
    private String state;

    @Column(name = "postal_code", nullable = false, length = 8)
    private String postalCode;

    @Column(name = "complement", nullable = false)
    private String complement;

    @Column(name = "neighborhood", nullable = false)
    private String neighborhood;

    @Column(name = "active", nullable = false)
    private boolean active;

    @Column(name = "created_at", nullable = false, columnDefinition = "TIMESTAMP")
    private Instant createdAt;

    @Column(name = "updated_at", nullable = false, columnDefinition = "TIMESTAMP")
    private Instant updatedAt;

    @Column(name = "deleted_at", columnDefinition = "TIMESTAMP")
    private Instant deletedAt;

    public AddressJpaEntity() {
    }

    private AddressJpaEntity(
            final String anId,
            final String street,
            final String streetNumber,
            final String city,
            final String state,
            final String postalCode,
            final String complement,
            final String neighborhood,
            final boolean active,
            final Instant createdAt,
            final Instant updatedAt,
            final Instant deletedAt
    ) {
        this.id = anId;
        this.street = street;
        this.streetNumber = streetNumber;
        this.city = city;
        this.state = state;
        this.postalCode = postalCode;
        this.complement = complement;
        this.neighborhood = neighborhood;
        this.active = active;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.deletedAt = deletedAt;
    }

    public static AddressJpaEntity from(final Address aAddress) {
        return new AddressJpaEntity(
                aAddress.getId().getValue(),
                aAddress.getStreet(),
                aAddress.getStreetNumber(),
                aAddress.getCity(),
                aAddress.getState(),
                aAddress.getPostalCode(),
                aAddress.getComplement(),
                aAddress.getNeighborhood(),
                aAddress.isActive(),
                aAddress.getCreatedAt(),
                aAddress.getUpdatedAt(),
                aAddress.getDeletedAt()
        );
    }

    public Address toAggregate() {
        return Address.with(
                AddressID.from(getId()),
                getStreet(),
                getStreetNumber(),
                getCity(),
                getState(),
                getPostalCode(),
                getComplement(),
                getNeighborhood(),
                isActive(),
                getCreatedAt(),
                getUpdatedAt(),
                getDeletedAt()
        );
    }

    public String getId() {
        return id;
    }

    public AddressJpaEntity setId(String id) {
        this.id = id;
        return this;
    }

    public String getStreet() {
        return street;
    }

    public AddressJpaEntity setStreet(String street) {
        this.street = street;
        return this;
    }

    public String getStreetNumber() {
        return streetNumber;
    }

    public AddressJpaEntity setStreetNumber(String streetNumber) {
        this.streetNumber = streetNumber;
        return this;
    }

    public String getCity() {
        return city;
    }

    public AddressJpaEntity setCity(String city) {
        this.city = city;
        return this;
    }

    public String getState() {
        return state;
    }

    public AddressJpaEntity setState(String state) {
        this.state = state;
        return this;
    }

    public String getPostalCode() {
        return postalCode;
    }

    public AddressJpaEntity setPostalCode(String postalCode) {
        this.postalCode = postalCode;
        return this;
    }

    public String getComplement() {
        return complement;
    }

    public AddressJpaEntity setComplement(String complement) {
        this.complement = complement;
        return this;
    }

    public String getNeighborhood() {
        return neighborhood;
    }

    public AddressJpaEntity setNeighborhood(String neighborhood) {
        this.neighborhood = neighborhood;
        return this;
    }

    public boolean isActive() {
        return active;
    }

    public AddressJpaEntity setActive(boolean active) {
        this.active = active;
        return this;
    }

    public Instant getCreatedAt() {
        return createdAt;
    }

    public AddressJpaEntity setCreatedAt(Instant createdAt) {
        this.createdAt = createdAt;
        return this;
    }

    public Instant getUpdatedAt() {
        return updatedAt;
    }

    public AddressJpaEntity setUpdatedAt(Instant updatedAt) {
        this.updatedAt = updatedAt;
        return this;
    }

    public Instant getDeletedAt() {
        return deletedAt;
    }

    public AddressJpaEntity setDeletedAt(Instant deletedAt) {
        this.deletedAt = deletedAt;
        return this;
    }
}
