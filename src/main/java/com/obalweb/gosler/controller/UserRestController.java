package com.obalweb.gosler.controller;

import com.obalweb.gosler.exception.InternalServerError;
import com.obalweb.gosler.model.User;
import com.obalweb.gosler.repository.UserRepository;
import com.obalweb.gosler.repository.UserRepositoryImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/users")
public class UserRestController {

    @Autowired
    UserRepositoryImpl userRepository;

    // Create user
    @PostMapping
    public ResponseEntity<String> save(@RequestBody User usr) {
        try {
            var userId = userRepository.saveAndReturnId(usr);

            return new ResponseEntity<String>("User successfully created , ID =" + userId,
                    HttpStatus.CREATED);
        } catch (Exception e) {
            throw new InternalServerError(e.getMessage());
        }
    }


}
