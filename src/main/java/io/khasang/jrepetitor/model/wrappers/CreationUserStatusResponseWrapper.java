package io.khasang.jrepetitor.model.wrappers;

public class CreationUserStatusResponseWrapper {
    private Boolean isLoginExist;
    private Boolean isPhoneExist;
    private Boolean isEmailExist;


    public CreationUserStatusResponseWrapper() {
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
