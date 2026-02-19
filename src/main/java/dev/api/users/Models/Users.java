package dev.api.users.Models;

import jakarta.persistence.*;
import lombok.NoArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "users")
@Getter
@Setter
@NoArgsConstructor

public class Users {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name="firstName",nullable = false)
    private String firstName;

    @Column(name="sureName",nullable = false)
    private String sureName;

    @Column(name="email",nullable = false)
    private String email;

    @Column(name ="age",nullable = false)
    private Integer age;

    @OneToMany(mappedBy = "user",fetch = FetchType.LAZY,cascade = CascadeType.ALL)
    private List<Address> addresses= new ArrayList<>();


    public Users(String firstName, String sureName, String email, Integer age) {
        this.firstName = firstName;
        this.sureName = sureName;
        this.email = email;
        this.age = age;
    }

    public void addAddress(Address address) {
        addresses.add(address);
        address.setUser(this);
    }

    public void removeAddress(Address address) {
        addresses.remove(address);
        address.setUser(null);
    }
}
