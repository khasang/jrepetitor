package io.khasang.jrepetitor.dto;

import io.khasang.jrepetitor.entity.Profile;

public interface ProfileDTOInterface {
    long getId();

    void setId(long id);

    String getName();

    void setName(String name);

    String getMiddlename();

    void setMiddlename(String middlename);

    String getSurname();

    void setSurname(String surname);

    String getEmail();

    void setEmail(String email);

    String getPhoneNumber();

    void setPhoneNumber(String phoneNumber);

    ProfileDTOInterface getProfileDTO(Profile profileDTO);

}
