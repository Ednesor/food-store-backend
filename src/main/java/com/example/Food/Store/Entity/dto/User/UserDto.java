package com.example.Food.Store.Entity.dto.User;

public record UserDto(
        Long id,
        String name,
        String lastname,
        String username,
        String email,
        String role
) {
}
