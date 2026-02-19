package dev.api.users.Models;


import jakarta.persistence.*;
import lombok.*;

import java.util.Objects;

@Entity
@Table(name = "address",
        uniqueConstraints = @UniqueConstraint(
                name = "uk_user_full_address",
                columnNames = {
                        "user_id",
                        "street",
                        "city",
                        "postal_Code",
                        "country"
                }))
@Setter
@Getter
@NoArgsConstructor

public class Address {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name="street",nullable = false)
    private String street;

    @Column(name="city",nullable = false)
    private String city;

    @Column(name="postalCode",nullable = false)
    private String postalCode;

    @Column(name="country",nullable = false)
    private String country;

    @Column(name="isMain")
    private Boolean isMain;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private Users user;

    public Address(String street, String city, String postalCode, String country,Boolean isMain) {
        this.street = street;
        this.city = city;
        this.postalCode = postalCode;
        this.country = country;
        this.isMain = isMain;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Address other)) return false;

        return street != null && street.equalsIgnoreCase(other.street)
                && city != null && city.equalsIgnoreCase(other.city)
                && postalCode != null && postalCode.equals(other.postalCode)
                && country != null && country.equalsIgnoreCase(other.country);
    }

    @Override
    public int hashCode() {
        return Objects.hash(
                street == null ? null : street.toLowerCase(),
                city == null ? null : city.toLowerCase(),
                postalCode,
                country == null ? null : country.toLowerCase()
        );
    }
}
