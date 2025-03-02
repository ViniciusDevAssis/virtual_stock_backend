package com.viniciusdevassis.stock.mapper;

import com.viniciusdevassis.stock.dto.CreateUserDTO;
import com.viniciusdevassis.stock.dto.ResponseUserDTO;
import com.viniciusdevassis.stock.dto.UpdateUserDTO;
import com.viniciusdevassis.stock.entities.User;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface UserMapper {

    CreateUserDTO userToCreateUserDTO(User user);
    User createUserDTOToUser(CreateUserDTO dto);
    ResponseUserDTO userToUserResponseDTO(User user);
    User responseUserDTOToUser(ResponseUserDTO dto);
    UpdateUserDTO userToUpdateUserDTO(User user);
    User updateUserDTOToUser(UpdateUserDTO dto);
}
