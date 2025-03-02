package com.viniciusdevassis.stock.mapper;

import com.viniciusdevassis.stock.dto.CreateUserDTO;
import com.viniciusdevassis.stock.dto.ResponseUserDTO;
import com.viniciusdevassis.stock.dto.UpdateUserDTO;
import com.viniciusdevassis.stock.entities.User;
import org.mapstruct.Mapper;

import java.util.List;
import java.util.Optional;

@Mapper(componentModel = "spring")
public interface UserMapper {

    //Converter um único user
    CreateUserDTO userToCreateUserDTO(User user);
    User createUserDTOToUser(CreateUserDTO dto);
    ResponseUserDTO userToResponseUserDTO(User user);
    User responseUserDTOToUser(ResponseUserDTO dto);
    UpdateUserDTO userToUpdateUserDTO(User user);
    User updateUserDTOToUser(UpdateUserDTO dto);

    //Converter uma lista de users
    List<ResponseUserDTO> usersToListDTO(List<User> users);
}
