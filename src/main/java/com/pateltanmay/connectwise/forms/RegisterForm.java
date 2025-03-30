package com.pateltanmay.connectwise.forms;

import org.hibernate.validator.constraints.Length;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class RegisterForm {

    @NotBlank(message = "Username should not be blank")
    @Length(min = 3, message = "Min 3 characters required")
    private String name;
    @NotBlank(message = "Email should not be blank")
    @Email(message = "Enter a valid email")
    private String email;
    @NotBlank(message = "Password must not be blank")
    private String password;
    @NotBlank(message = "Phone number is required")
    @Length(min = 10, max = 10, message = "Should be of length 10")
    private String phoneNumber;
    @NotBlank(message = "About should not be blank")
    private String about;
}
