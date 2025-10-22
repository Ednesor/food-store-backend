package com.example.Food.Store.Controller;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@CrossOrigin("*")
@RequestMapping("/api/alive")
public class IsAlive {

    @PostMapping
    public ResponseEntity<?> isAlive() {
        try{
            return ResponseEntity.ok("Esta vivo");
        }catch (Exception e){
            return ResponseEntity.badRequest().body("Ocurrio un error " + e.getMessage());
        }
    }
}
