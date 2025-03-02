package com.viniciusdevassis.stock.services;

import com.viniciusdevassis.stock.controllers.advice.exceptions.UserNotExistsException;
import com.viniciusdevassis.stock.dto.CreateUserDTO;
import com.viniciusdevassis.stock.dto.ResponseUserDTO;
import com.viniciusdevassis.stock.dto.UpdateUserDTO;
import com.viniciusdevassis.stock.entities.User;
import com.viniciusdevassis.stock.enums.Errors;
import com.viniciusdevassis.stock.enums.Status;
import com.viniciusdevassis.stock.mapper.UserMapper;
import com.viniciusdevassis.stock.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository repository;

    @Autowired
    private UserMapper mapper;

    public List<ResponseUserDTO> getAllUsers(){
        List<User> users = repository.findAll();
        return mapper.usersToListDTO(users);
    }

    public ResponseUserDTO getUserById(Long id){
        User user = repository.findById(id).orElseThrow(() -> new UserNotExistsException(Errors.UEE101));
        return mapper.userToResponseUserDTO(user);
    }

    public ResponseUserDTO createUser(CreateUserDTO dto){
        User user = mapper.createUserDTOToUser(dto);
        User savedUser = repository.save(user);
        return mapper.userToResponseUserDTO(savedUser);
    }

    public ResponseUserDTO updateUser(Long id, UpdateUserDTO dto){
        ResponseUserDTO userDto = getUserById(id);
        User user = mapper.responseUserDTOToUser(userDto);

        user.setName(dto.getName() != null ? dto.getName() : user.getName());
        user.setEmail(dto.getEmail() != null ? dto.getEmail() : user.getEmail());
        user.setPassword(dto.getPassword() != null ? dto.getPassword() : user.getPassword());

        User updatedUser = repository.save(user);
        return mapper.userToResponseUserDTO(updatedUser);

    }

    public void deactivateUserById(Long id){
        ResponseUserDTO userDto = getUserById(id);
        User user = mapper.responseUserDTOToUser(userDto);

        user.setStatus(Status.INATIVO);
        repository.save(user);
    }

    public void activateUserById(Long id) {
        ResponseUserDTO userDto = getUserById(id);
        User user = mapper.responseUserDTOToUser(userDto);

        user.setStatus(Status.ATIVO);
        repository.save(user);
    }

}
