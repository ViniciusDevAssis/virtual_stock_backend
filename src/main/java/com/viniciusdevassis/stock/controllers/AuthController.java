package com.viniciusdevassis.stock.controllers;

import com.viniciusdevassis.stock.dto.*;
import com.viniciusdevassis.stock.entities.User;
import com.viniciusdevassis.stock.mapper.UserMapper;
import com.viniciusdevassis.stock.repositories.UserRepository;
import com.viniciusdevassis.stock.security.TokenService;
import com.viniciusdevassis.stock.services.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.Optional;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private UserService service;

    @Autowired
    private UserMapper mapper;

    private final UserRepository repository;
    private final PasswordEncoder passwordEncoder;
    private final TokenService tokenService;

    public AuthController(UserRepository repository, PasswordEncoder passwordEncoder, TokenService tokenService) {
        this.repository = repository;
        this.passwordEncoder = passwordEncoder;
        this.tokenService = tokenService;
    }

    @PostMapping("/login")
    public ResponseEntity login(@RequestBody LoginRequestDTO body){
        User user = this.repository.findByEmail(body.email()).orElseThrow(() -> new RuntimeException("User not found"));
        if(passwordEncoder.matches(body.password(), user.getPassword())) {
            String token = this.tokenService.generateToken(user);
            return ResponseEntity.ok(new ResponseDTO(user.getName(), token));
        }
        return ResponseEntity.badRequest().build();
    }


    @PostMapping("/register")
    public ResponseEntity<ResponseDTO> registerUser(@Valid @RequestBody CreateUserDTO body){
        body.setPassword(passwordEncoder.encode(body.getPassword()));
        ResponseUserDTO user = service.createUser(body);
        User createdUser = mapper.responseUserDTOToUser(user);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/users/{id}")
                .buildAndExpand(createdUser.getId()).toUri();
        String token = this.tokenService.generateToken(createdUser);
        ResponseDTO response = new ResponseDTO(createdUser.getName(), token);
        return ResponseEntity.created(uri).body(response);
    }
}
