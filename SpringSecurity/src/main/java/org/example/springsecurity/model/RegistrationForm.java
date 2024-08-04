package org.example.springsecurity.model;


import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RegistrationForm {

    @NotNull
    @NotEmpty
    @Size(min = 3, max = 20)
    private String username;

    @NotNull
    @NotEmpty
    @Size(min = 3, max = 20)
    @Email
    private String email;

    @NotNull
    @NotEmpty
    @Size(min = 3, max = 20)
    private String password;
}
