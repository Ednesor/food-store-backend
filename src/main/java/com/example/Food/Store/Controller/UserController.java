package com.example.Food.Store.Controller;

import com.example.Food.Store.Entity.dto.User.UserCreate;
import com.example.Food.Store.Entity.dto.User.UserLogin;
import com.example.Food.Store.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@CrossOrigin("*")
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    UserService userService;

    @PostMapping("/signup")
    public ResponseEntity<?> save(@RequestBody UserCreate u) {
        try {
            return ResponseEntity.ok(userService.save(u));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Ocurrio un error: " + e.getMessage());
        }
    }

    @GetMapping("/login")
    public ResponseEntity<?> login(@RequestBody UserLogin u) {
        try {
            return ResponseEntity.ok(userService.login(u));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Ocurrio un error: " + e.getMessage());
        }
    }
}
