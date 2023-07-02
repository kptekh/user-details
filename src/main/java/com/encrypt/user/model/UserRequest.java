package com.encrypt.user.model;

import com.encrypt.user.config.PasswordUtils;
import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.beans.factory.annotation.Autowired;

import javax.validation.constraints.AssertTrue;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import java.io.Serializable;

public class UserRequest implements Serializable {

    @NotBlank(message = "fistName is mandatory")
    private String firstName;
    @NotBlank(message = "lastName is mandatory")
    private String lastName;
    @Email
    @NotBlank(message = "Email is mandatory")
    @Pattern(regexp=".+@.+\\.[a-z]+")
    private String email;
    @Email
    @NotBlank
    @Pattern(regexp=".+@.+\\.[a-z]+")
    private String confirmEmail;

    @NotBlank(message = "password must not be empty")
    private String password;
    @NotBlank(message = "password must not be empty")
    private String confirmPassword;

    @Autowired
    PasswordUtils passwordUtils;

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getConfirmEmail() {
        return confirmEmail;
    }

    public void setConfirmEmail(String confirmEmail) {
        this.confirmEmail = confirmEmail;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getConfirmPassword() {
        return confirmPassword;
    }

    public void setConfirmPassword(String confirmPassword) {

        this.confirmPassword = confirmPassword;
    }

    @JsonIgnore
    @AssertTrue(message = "Password and Confirm Password should match")
    public boolean isPasswordMatched(){
        return this.password.equals(this.confirmPassword);
    }

    @JsonIgnore
    @AssertTrue(message = "Email and Confirm Email should match")
    public boolean isEmailMatched(){
        return this.email.equals(this.confirmEmail);
    }

}
