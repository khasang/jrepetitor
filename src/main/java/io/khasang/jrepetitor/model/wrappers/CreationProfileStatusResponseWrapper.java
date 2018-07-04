package io.khasang.jrepetitor.model.wrappers;

public class CreationProfileStatusResponseWrapper {
    private Boolean isPhoneExist;
    private Boolean isEmailExist;

    public Boolean isOk() {
        return !(isPhoneExist || isEmailExist);
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
