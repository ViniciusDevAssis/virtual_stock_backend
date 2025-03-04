package com.viniciusdevassis.stock.services;

import com.viniciusdevassis.stock.controllers.advice.exceptions.UserEmailNotFoundException;
import com.viniciusdevassis.stock.controllers.advice.exceptions.UserIdNotFoundException;
import com.viniciusdevassis.stock.dto.CreateUserDTO;
import com.viniciusdevassis.stock.dto.ResponseUserDTO;
import com.viniciusdevassis.stock.dto.UpdateUserDTO;
import com.viniciusdevassis.stock.entities.User;
import com.viniciusdevassis.stock.enums.Errors;
import com.viniciusdevassis.stock.enums.Status;
import com.viniciusdevassis.stock.mapper.UserMapper;
import com.viniciusdevassis.stock.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserRepository repository;

    @Autowired
    private UserMapper mapper;

    @Autowired
    private PasswordEncoder encoder;

    public List<User> getAllUsers(){
        return repository.findAll();
    }

    public User getUserById(Long id){
        return repository.findById(id).orElseThrow(() -> new UserIdNotFoundException(Errors.UEE101));
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
                dto.getPassword() != null && !dto.getPassword().isBlank() ? encoder.encode(dto.getPassword()) : user.getPassword()
        );

        User updatedUser = repository.save(user);
        return mapper.userToResponseUserDTO(updatedUser);

    }

    @Transactional
    public void deactivateUserById(Long id){
        User user = getUserById(id);
        if (user.getStatus() != Status.INACTIVE) {
            user.setStatus(Status.INACTIVE);
            repository.save(user);
        }
    }

    @Transactional
    public void activateUserById(Long id) {
        User user = getUserById(id);
        if (user.getStatus() != Status.ACTIVE) {
            user.setStatus(Status.ACTIVE);
            repository.save(user);
        }
    }

    public ResponseUserDTO getUserByEmail(String email){
        User user = repository.findByEmail(email).orElseThrow(() -> new UserEmailNotFoundException(Errors.UEE102));
        return mapper.userToResponseUserDTO(user);
    }
}
