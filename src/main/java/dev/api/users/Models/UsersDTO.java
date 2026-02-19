package dev.api.users.Models;

import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class UsersDTO {
    private Long id;

    @NotBlank(message = "First name is required")
    private String firstName;

    @NotBlank(message = "Sure name is required")
    private String sureName;

    @NotBlank(message = "Email is required")
    @Email(message = "Invalid email format")
    private String email;

    @NotNull(message = "Age is required")
    @Min(value = 18, message = "User must be at least 18 years old")
    @Max(value = 100, message = "Invalid age")
    private Integer age;


    @Override
    public String toString() {
        return "UsersDTO{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", sureName=" + sureName +
                ", email=" + email +
                ", age ="+
                '}';
    }

}
