package com.example.ERS.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.context.annotation.Lazy;

import com.example.ERS.entity.User;
import com.example.ERS.entity.Reimbursement;
import com.example.ERS.service.ReimbursementService;
import com.example.ERS.service.UserService;
import com.example.ERS.service.JwtService;


@RestController
public class ERSController {
    
    @Autowired
    @Lazy
    private UserService userService;
    
    @Autowired
    @Lazy
    private ReimbursementService reimbursementService;

    @Autowired
    JwtService jwtService;

    @PostMapping("/register")
    public ResponseEntity registerUser(@RequestBody User user) {

        Optional<User> userOptional = Optional.ofNullable(userService.registerUser(user));
        if(userOptional.isPresent()) {
            return ResponseEntity.status(200).body(userOptional.toString());
        }
        return ResponseEntity.status(400).body(null);
    }

    @PostMapping("/login")
    public ResponseEntity loginUser(@RequestBody String username, @RequestBody String password) {
        Optional<String> tokenOptional = Optional.ofNullable(userService.loginUser(username, password));
        //gen token from id
        if(tokenOptional.isPresent()) {
            String jwt = tokenOptional.get();
            return ResponseEntity.status(200).body(jwt);
        }
        return ResponseEntity.status(401).body(null);
    }

    @PostMapping("/reimbursement")
    public ResponseEntity createTicket(@RequestBody String token, @RequestBody Reimbursement reimbursement) {
        Optional<Reimbursement> reimbursementOptional = Optional.ofNullable(reimbursementService.createReimbursement(token, reimbursement));
        if(reimbursementOptional.isPresent()) { 
            return ResponseEntity.status(200).body(reimbursementOptional.toString());
        }
        return ResponseEntity.status(401).body(null);
    }

    @PostMapping("/reimbursement/edit")
    public ResponseEntity editTicket(@RequestBody String token, @RequestBody Integer id, @RequestBody String status) {
        Optional<Reimbursement> reimbursementOptional = Optional.ofNullable(reimbursementService.updateReimbursement(token, id, status));
        if(reimbursementOptional.isPresent()) { 
            return ResponseEntity.status(200).body(reimbursementOptional.toString());
        }
        return ResponseEntity.status(401).body(null);
    }
    //reimbursements will use the designated token
    //on returning use the reimb response  dto
}
