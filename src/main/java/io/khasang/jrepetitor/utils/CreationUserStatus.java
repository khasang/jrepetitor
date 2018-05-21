package io.khasang.jrepetitor.utils;

public class CreationUserStatus {
    private Boolean isLoginExist;
    private Boolean isPhoneExist;
    private Boolean isEmailExist;


    public CreationUserStatus() {
    }

    public Boolean isOk() {
        return !(isLoginExist || isPhoneExist || isEmailExist);
    }

    public Boolean getLoginExist() {
        return isLoginExist;
    }

    public void setLoginExist(Boolean loginExist) {
        isLoginExist = loginExist;
    }

    public Boolean getPhoneExist() {
        return isPhoneExist;
    }

    public void setPhoneExist(Boolean phoneExist) {
        isPhoneExist = phoneExist;
    }

    public Boolean getEmailExist() {
        return isEmailExist;
    }

    public void setEmailExist(Boolean emailExist) {
        isEmailExist = emailExist;
    }


}
