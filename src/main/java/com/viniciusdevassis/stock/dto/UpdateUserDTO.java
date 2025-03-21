package com.viniciusdevassis.stock.dto;

import com.viniciusdevassis.stock.validators.EmailAvailable;
import com.viniciusdevassis.stock.validators.Name;
import com.viniciusdevassis.stock.validators.Password;
import jakarta.annotation.Nullable;
import jakarta.validation.constraints.NotNull;

public class UpdateUserDTO {

    @Name
    private String name;

    @EmailAvailable
    private String email;

    @Password
    private String password;

    public UpdateUserDTO() {
    }

    public UpdateUserDTO(String name, String email, String password) {
        this.name = name;
        this.email = email;
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
