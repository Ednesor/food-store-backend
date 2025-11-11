package com.example.Food.Store.Entity.dto.Mapper;

import com.example.Food.Store.Entity.User;
import com.example.Food.Store.Entity.dto.User.UserCreate;
import com.example.Food.Store.Entity.dto.User.UserDto;
import com.example.Food.Store.Entity.dto.User.UserEdit;

public class UserMapper {
    public static User toEntity(UserCreate u){
        return User.builder()
                .name(u.name())
                .lastname(u.lastname())
                .username(u.username())
                .email(u.email())
                .password(u.password())
                .build();
    }

    public static UserDto toDto(User u){
        if (u==null) return null;
        // Comprobamos si el rol es nulo antes de llamar a .name()
        String roleName = (u.getRole() != null) ? u.getRole().name() : null;
        return new UserDto(
                u.getId(),
                u.getName(),
                u.getLastname(),
                u.getUsername(),
                u.getEmail(),
                roleName
        );
    }

    public static void updateEntity(User user, UserEdit edit){
        user.setName(edit.name());
        user.setLastname(edit.lastname());
        user.setUsername(edit.username());
        user.setEmail(edit.email());
        user.setPassword(edit.password());
    }
}
