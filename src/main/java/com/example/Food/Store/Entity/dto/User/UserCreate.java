package com.example.Food.Store.Entity.dto.User;

public record UserCreate(
        String name,
        String lastname,
        String username,
        String email,
        String password
) {
}
