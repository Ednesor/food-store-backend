package com.example.Food.Store.Service;

import com.example.Food.Store.Entity.User;
import com.example.Food.Store.Entity.dto.Mapper.UserMapper;
import com.example.Food.Store.Entity.dto.User.UserCreate;
import com.example.Food.Store.Entity.dto.User.UserDto;
import com.example.Food.Store.Entity.dto.User.UserEdit;
import com.example.Food.Store.Entity.dto.User.UserLogin;
import com.example.Food.Store.Repository.UserRepository;
import com.example.Food.Store.Security.PasswordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImp implements UserService{

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordService passwordService;

    @Override
    public UserDto save(UserCreate u) {
        User alreadyCreated = userRepository.findByEmail(u.email()).orElse(null);
        if (alreadyCreated != null) {
            throw new RuntimeException("El email ya esta en uso");
        }
        User user = UserMapper.toEntity(u);
        user.setPassword(passwordService.hashPassword(user.getPassword()));
        user = userRepository.save(user);

        return UserMapper.toDto(user);
    }

    @Override
    public UserDto findById(Long id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new NullPointerException("No se encontro el ususario con el id " + id));
        return UserMapper.toDto(user);
    }

    @Override
    public void delete(Long id) {
        userRepository.deleteById(id);
    }

    @Override
    public UserDto edit(UserEdit u, Long id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new NullPointerException("No se encontro el ususario con el id " + id));
        UserMapper.updateEntity(user, u);
        user = userRepository.save(user);
        return UserMapper.toDto(user);
    }

    @Override
    public UserDto login(UserLogin userLogin) {
        System.out.println(userLogin.password());
        User user = userRepository.findByEmail(userLogin.email())
                .orElseThrow(() -> new RuntimeException("Email o contraseña inválidos"));

        if (!passwordService.verifyPassword(userLogin.password(), user.getPassword())) {
            throw new RuntimeException("Email o contraseña inválidos");
        }

        return UserMapper.toDto(user);
    }
}
