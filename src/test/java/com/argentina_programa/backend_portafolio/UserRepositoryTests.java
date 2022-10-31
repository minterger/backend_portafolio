package com.argentina_programa.backend_portafolio;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.annotation.Rollback;

import com.argentina_programa.backend_portafolio.models.User;
import com.argentina_programa.backend_portafolio.repositories.UserRepository;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@AutoConfigureTestDatabase(replace = Replace.NONE)
@Rollback(false)
public class UserRepositoryTests {
    @Autowired
    UserRepository repository;

    @Test
    public void testCreatedUser() {
        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

        String rawPassword = "ArgentinaPrograma";
        String encodePassword = passwordEncoder.encode(rawPassword);
        User newUser = new User("leonelmenci@gmail.com", encodePassword);
        System.out.println(newUser);
        User savedUser = repository.save(newUser);
        assertThat(savedUser).isNotNull();
        assertThat(savedUser.getId()).isGreaterThan(0);
    }

}
