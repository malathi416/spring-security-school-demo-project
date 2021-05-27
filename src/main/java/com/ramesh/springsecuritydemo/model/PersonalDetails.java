package com.ramesh.springsecuritydemo.model;

import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.Entity;
import javax.validation.constraints.*;
import java.time.LocalDate;

@Entity
public class PersonalDetails extends AbstractEntity {

    @NotBlank(message = "First Name is required")
    @Size(min = 3, max = 50, message = "Name must be between 3 - 50 characters")
    private String firstName;

    @NotBlank(message = "Last Name is required")
    @Size(min = 1, max = 50, message = "Name must be between 1 - 50 characters")
    private String lastName;

    @NotBlank(message = "Phone Number is required")
    @Size(min = 10, max = 10, message = "Must be 10 digits")
    private String phoneNumber;

    @NotBlank(message = "Email is required")
    @Email(message = "Invalid Email. Try Again!!")
    private String email;

    @NotNull(message = "Please enter Date Of Birth")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @Past(message = "Please Enter Correct Date Of Birth")
    private LocalDate dateOfBirth;

    public PersonalDetails() {

    }

    public PersonalDetails(String firstName, String lastName, String phoneNumber, String email, LocalDate dateOfBirth) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.dateOfBirth = dateOfBirth;
    }

    public LocalDate getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(LocalDate dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

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

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

}
