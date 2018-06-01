package io.khasang.jrepetitor.dto.impl;

import io.khasang.jrepetitor.dto.ProfileDTOInterface;
import io.khasang.jrepetitor.entity.Profile;

public class ProfileDTOImpl implements ProfileDTOInterface {
    private long id;
    private String name;
    private String middlename;
    private String surname;
    private String email;
    private String phoneNumber;


    public ProfileDTOImpl() {

    }

    private ProfileDTOImpl(Profile profile) {
        id = profile.getId();
        name = profile.getName();
        middlename = profile.getMiddlename();
        surname = profile.getSurname();
        email = profile.getEmail();
        phoneNumber = profile.getPhoneNumber();
    }

    @Override
    public long getId() {
        return id;
    }

    @Override
    public void setId(long id) {
        this.id = id;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String getMiddlename() {
        return middlename;
    }

    @Override
    public void setMiddlename(String middlename) {
        this.middlename = middlename;
    }

    @Override
    public String getSurname() {
        return surname;
    }

    @Override
    public void setSurname(String surname) {
        this.surname = surname;
    }

    @Override
    public String getEmail() {
        return email;
    }

    @Override
    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String getPhoneNumber() {
        return phoneNumber;
    }

    @Override
    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    @Override
    public ProfileDTOInterface getProfileDTO(Profile profile) {
        return new ProfileDTOImpl(profile);

    }
}
