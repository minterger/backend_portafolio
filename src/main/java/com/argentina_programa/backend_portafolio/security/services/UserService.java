package com.argentina_programa.backend_portafolio.security.services;

import java.util.Optional;

import javax.transaction.Transactional;

import com.argentina_programa.backend_portafolio.security.entities.User;
import com.argentina_programa.backend_portafolio.security.respositories.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class UserService {
    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public Optional<User> getByUserName(String userName) {
        return userRepository.findByUserName(userName);
    }

    public boolean existByUserName(String userName) {
        return userRepository.existsByUserName(userName);
    }

    public void save(User user) {
        userRepository.save(user);
    }

}
