package com.uniqhorn.entity;

import javax.validation.constraints.NotBlank;

public class PasswordUpdate {
    private String oldPassword;
    public String getOldPassword() {
        return oldPassword;
    }
    private String newPassword;
    public String getNewPassword() {
        return newPassword;
    }
    private String newPasswordCheck;
    public String getNewPasswordCheck() {
        return newPasswordCheck;
    }
}