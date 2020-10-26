package io.khasang.jrepetitor.model.wrappers;

import io.khasang.jrepetitor.entity.Profile;

public class ProfileWrapper {
    private String name;
    private String middleName;
    private String surname;
    private String email;
    private String phoneNumber;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMiddleName() {
        return middleName;
    }

    public void setMiddleName(String middleName) {
        this.middleName = middleName;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public Profile getProfile() {
        Profile profile = new Profile();
        profile.setName(name);
        profile.setMiddleName(middleName);
        profile.setSurname(surname);
        profile.setEmail(email);
        profile.setPhoneNumber(phoneNumber);
        return profile;
    }
}
