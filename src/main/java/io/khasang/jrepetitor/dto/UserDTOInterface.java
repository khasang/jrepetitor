package io.khasang.jrepetitor.dto;

import io.khasang.jrepetitor.entity.User;

import java.util.List;

public interface UserDTOInterface {

    List<UserDTOInterface> getUserDTOList(List<User> user);

    UserDTOInterface getUserDTO(User user);

    long getId();

    void setId(long id);

    String getName();

    void setName(String name);

    String getLogin();

    void setLogin(String login);

    String getPassword();

    void setPassword(String password);

    String getRoleName();

    void setRoleName(String roleName);

    ProfileDTOInterface getProfile();

    void setProfile(ProfileDTOInterface profile);
}
