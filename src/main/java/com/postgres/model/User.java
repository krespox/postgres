package com.postgres.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
@Entity
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    @Size(max = 50)
    @Pattern(regexp = "^[A-Z][a-z]*$", message = "First name must start with a capital letter and contain no spaces")
    private String firstName;

    @NotBlank
    @Size(max = 50)
    @Pattern(regexp = "^[A-Z][a-z]*$", message = "Last name must start with a capital letter and contain no spaces")
    private String lastName;
}
