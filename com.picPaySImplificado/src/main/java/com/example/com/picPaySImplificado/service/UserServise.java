package com.example.com.picPaySImplificado.service;


import com.example.com.picPaySImplificado.UserRepository.UserRepository;
import com.example.com.picPaySImplificado.domain.user.User;
import com.example.com.picPaySImplificado.domain.user.UserType;
import com.example.com.picPaySImplificado.dto.UserDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.expression.ExpressionException;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
public class UserServise {

    @Autowired
    private UserRepository repository;

    public void validateTransection(User sender, BigDecimal amount) throws Exception {
        if (sender.getUserType() == UserType.MERCHANT) {
            throw new Exception("Usuario do tipo lojista não está autorizado a realizar transação");
        }
        if (sender.getBalance().compareTo(amount) < 0) {
            throw new Exception("Saldo insuficiente. ");

        }
    }

    public User findUserById(Long id) throws Exception {
        return this.repository.findUserById(id).orElseThrow(() -> new Exception("Usuario não encontrado. "));
    }


    public User createUser(UserDTO data) {
        User newUser =  new User(data);
        this.saveUser(newUser);

        return newUser;
    }


    public List<User> getAllUsers() {
       return this.repository.findAll();

    }

     public void saveUser(User user) {
        this.repository.save(user);
     }

}
