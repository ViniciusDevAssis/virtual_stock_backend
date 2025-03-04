package com.viniciusdevassis.stock.controllers;

import com.viniciusdevassis.stock.dto.ResponseUserDTO;
import com.viniciusdevassis.stock.dto.CreateUserDTO;
import com.viniciusdevassis.stock.dto.UpdateUserDTO;
import com.viniciusdevassis.stock.entities.User;
import com.viniciusdevassis.stock.mapper.UserMapper;
import com.viniciusdevassis.stock.services.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService service;

    @Autowired
    private UserMapper mapper;

    @GetMapping
    public ResponseEntity<List<ResponseUserDTO>> getAllUsers(){
        List<User> users = service.getAllUsers();
        List<ResponseUserDTO> response = mapper.usersToListDTO(users);
        return ResponseEntity.ok(response);
    }

    @GetMapping(value = "id/{id}")
    public ResponseEntity<ResponseUserDTO> getUserById(@PathVariable Long id){
        User user = service.getUserById(id);
        ResponseUserDTO userDTO = mapper.userToResponseUserDTO(user);
        return ResponseEntity.ok(userDTO);
    }

    @PostMapping
    public ResponseEntity<ResponseUserDTO> createUser(@Valid @RequestBody CreateUserDTO dto) {
        ResponseUserDTO createdUser = service.createUser(dto);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(createdUser.getId()).toUri();
        return ResponseEntity.created(uri).body(createdUser);
    }

    @PatchMapping("/update/{id}")
    public ResponseEntity<ResponseUserDTO> updateUser(@PathVariable Long id, @RequestBody UpdateUserDTO dto) {
        ResponseUserDTO updatedUser = service.updateUser(id, dto);
        return ResponseEntity.ok(updatedUser);
    }

    @PatchMapping("/deactivate/{id}")
    public ResponseEntity<Void> deactivateUser(@PathVariable Long id) {
        service.deactivateUserById(id);
        return ResponseEntity.noContent().build();
    }

    @PatchMapping("/activate/{id}")
    public ResponseEntity<Void> activateUser(@PathVariable Long id) {
        service.activateUserById(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("email/{email}")
    public ResponseEntity<ResponseUserDTO> getUserByEmail(@PathVariable String email) {
        ResponseUserDTO user = service.getUserByEmail(email);
        return ResponseEntity.ok(user);
    }
}
