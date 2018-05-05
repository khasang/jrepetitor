package io.khasang.jrepetitor.dto;

import io.khasang.jrepetitor.entity.Profile;
import io.khasang.jrepetitor.entity.User;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class UserDTO {
    private long id;
    private String name;
    private String login;
    private String password;
    private String roleName;
    private ProfileDTO profile;

    public List<UserDTO> getUserDTOList(List<User> user) {
        List<UserDTO> userDTOList = new ArrayList<>();
        for (User inputUser : user) {
            UserDTO userDTO = new UserDTO();
            userDTO.setName(inputUser.getName());
            userDTO.setLogin(inputUser.getLogin());
            userDTO.setId(inputUser.getId());
            userDTO.setPassword(inputUser.getPassword());
            userDTO.setRoleName(inputUser.getRoleName());

            ProfileDTO profile = new ProfileDTO();
            profile.setId(inputUser.getProfile().getId());
            profile.setName(inputUser.getProfile().getName());
            profile.setMiddlename(inputUser.getProfile().getMiddlename());
            profile.setSurname(inputUser.getProfile().getSurname());
            profile.setEmail(inputUser.getProfile().getEmail());
            profile.setPhoneNumber(inputUser.getProfile().getPhoneNumber());
            userDTO.setProfile(profile);
            userDTOList.add(userDTO);
        }
        return userDTOList;
    }

    public UserDTO getUserDTO(User user) {
        if (user == null) {
            return null;
        }
        UserDTO userDTO = new UserDTO();
        userDTO.setName(user.getName());
        userDTO.setLogin(user.getLogin());
        userDTO.setId(user.getId());
        userDTO.setPassword(user.getPassword());
        userDTO.setRoleName(user.getRoleName());

        ProfileDTO profile = new ProfileDTO();

        profile.setId(user.getProfile().getId());
        profile.setName(user.getProfile().getName());
        profile.setMiddlename(user.getProfile().getMiddlename());
        profile.setSurname(user.getProfile().getSurname());
        profile.setEmail(user.getProfile().getEmail());
        profile.setPhoneNumber(user.getProfile().getPhoneNumber());

        userDTO.setProfile(profile);
        return userDTO;
    }

    public User getUser(UserDTO userDTO) {
        if (userDTO == null) {
            return null;
        }
        User user = new User();
        Profile profile = new Profile();
        ProfileDTO profileDTO = userDTO.getProfile();
        profile.setId(profileDTO.getId());
        profile.setName(profileDTO.getName());
        profile.setMiddlename(profileDTO.getMiddlename());
        profile.setSurname(profileDTO.getSurname());
        profile.setEmail(profileDTO.getEmail());
        profile.setPhoneNumber(profileDTO.getPhoneNumber());

        user.setId(userDTO.getId());
        user.setLogin(userDTO.getLogin());
        user.setName(userDTO.getName());
        user.setPassword(userDTO.getPassword());
        user.setRoleName(userDTO.getRoleName());
        user.setProfile(profile);
        return user;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

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

    public ProfileDTO getProfile() {
        return profile;
    }

    public void setProfile(ProfileDTO profile) {
        this.profile = profile;
    }
}
