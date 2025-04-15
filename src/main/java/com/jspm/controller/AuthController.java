//package com.jspm.controller;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestBody;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RestController;
//import org.springframework.web.bind.annotation.CrossOrigin;
//import com.jspm.model.User;
//import com.jspm.service.UserService;
//
//@RestController
//@RequestMapping("/api/auth")
//public class AuthController {
//
//    @Autowired
//    private UserService userService;
//    @CrossOrigin(origins = "http://localhost:5173")
//    @PostMapping("/login")
//    public ResponseEntity<?> login(@RequestBody User user) {
//        // Check if the user exists and if the password is correct
//        boolean isAuthenticated = userService.authenticate(user.getUsername(), user.getPassword());
//        if (isAuthenticated) {
//            return ResponseEntity.ok("Login successful");
//        } else {
//            return ResponseEntity.status(401).body("Invalid username or password");
//        }
//    }
//    @CrossOrigin(origins = "http://localhost:5173")
//    @PostMapping("/register")
//    public ResponseEntity<?> register(@RequestBody User user) {
//        if (userService.existsByUsername(user.getUsername())) {
//            return ResponseEntity.badRequest().body("Username is already taken");
//        }
//        User registeredUser = userService.save(user);
//        return ResponseEntity.ok(registeredUser);
//    }
//}


package com.jspm.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.jspm.model.User;
import com.jspm.service.UserService;

import java.util.HashMap;
import java.util.Map;
import com.jspm.util.JwtUtil;
@RestController
@RequestMapping("/api/auth")
@CrossOrigin(origins = "http://localhost:5173") // You can move this to class-level
public class AuthController {

    @Autowired
    private UserService userService;

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody User user) {
        boolean isAuthenticated = userService.authenticate(user.getUsername(), user.getPassword());

        if (isAuthenticated) {
            String token = JwtUtil.generateToken(user.getUsername());

            // Return token and username in JSON response
            Map<String, Object> response = new HashMap<>();
            response.put("message", "Login successful");
            response.put("token", token);
            response.put("username", user.getUsername());

            return ResponseEntity.ok(response);
        } else {
            return ResponseEntity.status(401).body("Invalid username or password");
        }
    }

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody User user) {
        if (userService.existsByUsername(user.getUsername())) {
            return ResponseEntity.badRequest().body("Username is already taken");
        }
        User registeredUser = userService.save(user);
        return ResponseEntity.ok(registeredUser);
    }
}

