package com.tecnicalTest.PicPay.controllers;

import com.tecnicalTest.PicPay.model.user.User;
import com.tecnicalTest.PicPay.dtos.UserDTO;
import com.tecnicalTest.PicPay.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController()
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping
    public ResponseEntity<User> createUser(@RequestBody UserDTO userDTO){
        User user = userService.createUser(userDTO);

        return new ResponseEntity<>(user, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<User>> getAllUsers(){
        return this.userService.getAllUsers();
    }
}
