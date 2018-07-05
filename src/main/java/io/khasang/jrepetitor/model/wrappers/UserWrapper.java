package io.khasang.jrepetitor.model.wrappers;

import io.khasang.jrepetitor.entity.User;

public class UserWrapper {

    private String name;
    private String login;
    private String password;
    private ProfileWrapper profile;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public ProfileWrapper getProfile() {
        return profile;
    }

    public void setProfile(ProfileWrapper profile) {
        this.profile = profile;
    }

    public User getUser() {
        User user = new User();
        user.setName(name);
        user.setLogin(login);
        user.setPassword(password);
        if (profile != null) {
            user.setProfile(profile.getProfile());
        } else {
            user.setProfile(null);
        }
        return user;
    }
}
