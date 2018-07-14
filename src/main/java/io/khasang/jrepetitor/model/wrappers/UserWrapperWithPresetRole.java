package io.khasang.jrepetitor.model.wrappers;

import io.khasang.jrepetitor.entity.User;

public class UserWrapperWithPresetRole extends UserWrapper {
    String roleName;

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    @Override
    public User getUser() {
        User user = super.getUser();
        user.setRoleName(roleName);
        return user;
    }
}
