package com.ramesh.springsecuritydemo.model;


import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;


@Entity
public class Teacher extends AbstractEntity {

    @OneToOne(cascade = CascadeType.ALL)
    @Valid
    @NotNull
    private PersonalDetails personalDetails;

    @Valid
    @NotNull(message = "Gender is required")
    private Gender gender;

    @ManyToOne
    @Valid
    @NotNull(message = "Class is required")
    private Classes classes;

    public Teacher() {
    }

    public Teacher(Gender gender, Classes classes) {
        this.gender = gender;
        this.classes = classes;
    }

    public PersonalDetails getPersonalDetails() {
        return personalDetails;
    }

    public void setPersonalDetails(PersonalDetails personalDetails) {
        this.personalDetails = personalDetails;
    }

    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }

    public Classes getClasses() {
        return classes;
    }

    public void setClasses(Classes classes) {
        this.classes = classes;
    }
}
