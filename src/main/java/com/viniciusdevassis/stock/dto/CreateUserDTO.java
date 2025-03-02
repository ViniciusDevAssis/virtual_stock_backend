package com.viniciusdevassis.stock.dto;

import com.viniciusdevassis.stock.validators.EmailAvailable;
import com.viniciusdevassis.stock.validators.Name;
import com.viniciusdevassis.stock.validators.Password;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

public class CreateUserDTO {

    @NotBlank(message = "O campo nome é obrigatório")
    @Name
    private String name;

    @NotBlank(message = "O campo email é obrigatório")
    @EmailAvailable
    private String email;

    @NotBlank(message = "O campo senha é obrigatório")
    @Password
    private String password;

    public CreateUserDTO() {
    }

    public CreateUserDTO(String name, String email, String password) {
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
