package io.khasang.jrepetitor.model.wrappers;

public class UserWrapper {

    private String name;
    private String login;
    private String password;
    private String roleName;
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

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    public ProfileWrapper getProfile() {
        return profile;
    }

    public void setProfile(ProfileWrapper profile) {
        this.profile = profile;
    }
}
