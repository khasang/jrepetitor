package io.khasang.jrepetitor.dao;

import io.khasang.jrepetitor.entity.Profile;

public interface ProfileDao extends BasicDao<Profile> {
    /**
     * method for finding profile by email
     *
     * @param email = email for search
     * @return user Profile with email - email
     */
    Profile getProfileByEmail(String email);

    /**
     * method for finding profile by phoneNumber
     *
     * @param phoneNumber = user phone number for search
     * @return profile with phoneNumber - phone number
     */
    Profile getProfileByPhone(String phoneNumber);
}
