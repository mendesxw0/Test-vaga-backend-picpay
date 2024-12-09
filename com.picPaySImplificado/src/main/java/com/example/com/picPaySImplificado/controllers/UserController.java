package com.example.com.picPaySImplificado.controllers;


import com.example.com.picPaySImplificado.domain.user.User;
import com.example.com.picPaySImplificado.dto.UserDTO;
import com.example.com.picPaySImplificado.service.UserServise;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController()
@RequestMapping("/users")
public class UserController {


    @Autowired
    private UserServise userServise;

    @PostMapping
    public ResponseEntity<User> createUser(@RequestBody UserDTO user) {
        User newUser = userServise.createUser(user);

        return new ResponseEntity<>(newUser, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<User>>  getAllUsers() {
        List<User> users = this.userServise.getAllUsers();

        return new ResponseEntity<>(users, HttpStatus.OK);
    }
}
