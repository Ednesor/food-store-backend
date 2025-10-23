package com.example.Food.Store.Entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.*;
import lombok.experimental.SuperBuilder;

@Entity
@Table(name = "users")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
public class User extends Base{
    private String name;
    private String lastname;
    private String username;
    private String email;
    private String password;
    private String role;
}
