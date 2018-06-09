package io.khasang.jrepetitor.dto;

import io.khasang.jrepetitor.entity.User;

import java.util.List;

public interface UserDTOInterface {

    List<UserDTOInterface> getUserDTOList(List<User> user);

    UserDTOInterface getUserDTO(User user);
}
