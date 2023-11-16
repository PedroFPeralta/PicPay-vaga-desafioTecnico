package com.tecnicalTest.PicPay.services;

import com.tecnicalTest.PicPay.model.user.User;
import com.tecnicalTest.PicPay.model.user.UserType;
import com.tecnicalTest.PicPay.dtos.UserDTO;
import com.tecnicalTest.PicPay.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
public class UserService {
    @Autowired
    private UserRepository repository;

    public void validateTransaction(User payer, BigDecimal value) throws Exception {
        if (payer.getUserType() == UserType.MERCHANT){
            throw new Exception("User is not authorized to complete a transaction");
        }

        if (payer.getBalance().compareTo(value) < 0){
            throw new Exception("Unable to complete a transaction: No enough balance");
        }
    }

    public User findUserById(Long id) throws Exception {
        return repository.findUserById(id).orElseThrow(() -> new Exception("User do not exist!"));
    }

    public void SaveUser(User user){
        repository.save(user);
    }

    public User createUser(UserDTO userDTO) {
        User user = new User(userDTO);
        this.SaveUser(user);

        return user;
    }

    public ResponseEntity<List<User>> getAllUsers() {
        List<User> allUsers = repository.findAll();

        return new ResponseEntity<>(allUsers, HttpStatus.OK);
    }
}
