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
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserRepository repository;

    @Autowired
    private UserMapper mapper;

    public List<User> getAllUsers(){
        return repository.findAll();
    }

    public User getUserById(Long id){
        return repository.findById(id).orElseThrow(() -> new UserNotExistsException(Errors.UEE101));
    }

    @Transactional
    public ResponseUserDTO createUser(CreateUserDTO dto){
        User user = mapper.createUserDTOToUser(dto);
        User savedUser = repository.save(user);
        return mapper.userToResponseUserDTO(savedUser);
    }

    @Transactional
    public ResponseUserDTO updateUser(Long id, UpdateUserDTO dto){
        User user = getUserById(id);

        user.setName(
                dto.getName() != null && !dto.getName().isBlank() ? dto.getName() : user.getName()
        );
        user.setEmail(
                dto.getEmail() != null ? dto.getEmail() : user.getEmail()
        );
        user.setPassword(
                dto.getPassword() != null && !dto.getPassword().isBlank() ? dto.getPassword() : user.getPassword()
        );

        User updatedUser = repository.save(user);
        return mapper.userToResponseUserDTO(updatedUser);

    }

    @Transactional
    public void deactivateUserById(Long id){
        User user = getUserById(id);
        if (user.getStatus() != Status.INATIVO) {
            user.setStatus(Status.INATIVO);
            repository.save(user);
        }
    }

    @Transactional
    public void activateUserById(Long id) {
        User user = getUserById(id);
        if (user.getStatus() != Status.ATIVO) {
            user.setStatus(Status.ATIVO);
            repository.save(user);
        }
    }

}
