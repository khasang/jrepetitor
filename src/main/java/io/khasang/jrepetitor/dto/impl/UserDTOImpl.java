package io.khasang.jrepetitor.dto.impl;

import io.khasang.jrepetitor.dto.ProfileDTOInterface;
import io.khasang.jrepetitor.dto.UserDTOInterface;
import io.khasang.jrepetitor.entity.User;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class UserDTOImpl implements UserDTOInterface {
    private long id;
    private String name;
    private String login;
    private String password;
    private String roleName;
    private ProfileDTOInterface profile;


    @Override
    public List<UserDTOInterface> getUserDTOList(List<User> user) {
        List<UserDTOInterface> userDTOList = new ArrayList<>();
        for (User inputUser : user) {
            UserDTOImpl userDTOImpl = new UserDTOImpl();
            userDTOImpl.setName(inputUser.getName());
            userDTOImpl.setLogin(inputUser.getLogin());
            userDTOImpl.setId(inputUser.getId());
            userDTOImpl.setPassword(inputUser.getPassword());
            userDTOImpl.setRoleName(inputUser.getRoleName());

            ProfileDTOImpl profile = new ProfileDTOImpl();
            profile.setId(inputUser.getProfile().getId());
            profile.setName(inputUser.getProfile().getName());
            profile.setMiddlename(inputUser.getProfile().getMiddlename());
            profile.setSurname(inputUser.getProfile().getSurname());
            profile.setEmail(inputUser.getProfile().getEmail());
            profile.setPhoneNumber(inputUser.getProfile().getPhoneNumber());
            userDTOImpl.setProfile(profile);
            userDTOList.add(userDTOImpl);
        }
        return userDTOList;
    }

    @Override
    public UserDTOInterface getUserDTO(User user) {
        if (user == null) {
            return null;
        }
        UserDTOImpl userDTOImpl = new UserDTOImpl();
        userDTOImpl.setName(user.getName());
        userDTOImpl.setLogin(user.getLogin());
        userDTOImpl.setId(user.getId());
        userDTOImpl.setPassword(user.getPassword());
        userDTOImpl.setRoleName(user.getRoleName());

        ProfileDTOImpl profile = new ProfileDTOImpl();

        profile.setId(user.getProfile().getId());
        profile.setName(user.getProfile().getName());
        profile.setMiddlename(user.getProfile().getMiddlename());
        profile.setSurname(user.getProfile().getSurname());
        profile.setEmail(user.getProfile().getEmail());
        profile.setPhoneNumber(user.getProfile().getPhoneNumber());

        userDTOImpl.setProfile(profile);
        return userDTOImpl;
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
    public String getLogin() {
        return login;
    }

    @Override
    public void setLogin(String login) {
        this.login = login;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String getRoleName() {
        return roleName;
    }

    @Override
    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    @Override
    public ProfileDTOInterface getProfile() {
        return profile;
    }

    @Override
    public void setProfile(ProfileDTOInterface profile) {
        this.profile = profile;
    }
}
