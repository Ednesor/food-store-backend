package com.example.Food.Store.Service;

import com.example.Food.Store.Entity.dto.User.UserCreate;
import com.example.Food.Store.Entity.dto.User.UserDto;
import com.example.Food.Store.Entity.dto.User.UserEdit;
import com.example.Food.Store.Entity.dto.User.UserLogin;

public interface UserService {
    UserDto save(UserCreate u);
    UserDto edit(UserEdit u, Long id);
    UserDto findById(Long id);
    UserDto login(UserLogin u);
    void delete(Long id);
}
