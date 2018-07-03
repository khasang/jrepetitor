package io.khasang.jrepetitor.dto.impl;

import io.khasang.jrepetitor.dto.UserDTOInterface;
import io.khasang.jrepetitor.entity.User;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class UserPreviewDTOImpl implements UserDTOInterface {
    private Long id;
    private String name;
    private String login;
    private String email;

    @Override
    public List<UserDTOInterface> getUserDTOList(List<User> user) {
        List<UserDTOInterface> userPreviewDTO = new ArrayList<>();
        if (user.isEmpty()) {
            return userPreviewDTO;
        }
        for (User currentUser : user) {
            userPreviewDTO.add(getUserDTO(currentUser));
        }

        return userPreviewDTO;
    }

    @Override
    public UserDTOInterface getUserDTO(User user) {
        if (user == null) {
            return null;
        }
        UserPreviewDTOImpl userPreviewDTO = new UserPreviewDTOImpl();
        userPreviewDTO.setId(user.getId());
        userPreviewDTO.setName(user.getName());
        userPreviewDTO.setLogin(user.getLogin());
        userPreviewDTO.setEmail(user.getProfile().getEmail());
        return userPreviewDTO;
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

}
