package io.khasang.jrepetitor.model.wrappers;

import io.khasang.jrepetitor.entity.User;

public class UserWrapperWithPresetRole extends UserWrapper {
    String role;

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    @Override
    public User getUser() {
        User user = super.getUser();
        user.setRoleName(role);
        return user;
    }
}
