package com.argentina_programa.backend_portafolio.security.controllers;

import java.util.HashSet;
import java.util.Set;

import javax.validation.Valid;

import com.argentina_programa.backend_portafolio.Exceptions.ResourceNotFoundException;
import com.argentina_programa.backend_portafolio.security.dtos.JwtDto;
import com.argentina_programa.backend_portafolio.security.dtos.LoginUser;
import com.argentina_programa.backend_portafolio.security.dtos.NewUser;
import com.argentina_programa.backend_portafolio.security.entities.Role;
import com.argentina_programa.backend_portafolio.security.entities.User;
import com.argentina_programa.backend_portafolio.security.enums.RoleList;
import com.argentina_programa.backend_portafolio.security.jwt.JwtProvider;
import com.argentina_programa.backend_portafolio.security.services.RoleService;
import com.argentina_programa.backend_portafolio.security.services.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthController {
    private final AuthenticationManagerBuilder authenticationManagerBuilder;
    private final PasswordEncoder passwordEncoder;
    private final UserService userService;
    private final RoleService roleService;
    private final JwtProvider jwtProvider;

    @Autowired
    public AuthController(AuthenticationManagerBuilder authenticationManagerBuilder, PasswordEncoder passwordEncoder,
            UserService userService, RoleService roleService, JwtProvider jwtProvider) {
        this.authenticationManagerBuilder = authenticationManagerBuilder;
        this.passwordEncoder = passwordEncoder;
        this.userService = userService;
        this.roleService = roleService;
        this.jwtProvider = jwtProvider;
    }

    @PostMapping("/login")
    public ResponseEntity<Object> login(@Valid @RequestBody LoginUser loginUser, BindingResult bidBindingResult) {
        if (bidBindingResult.hasErrors())
            return new ResponseEntity<>(new ResourceNotFoundException("Revise sus credenciales"),
                    HttpStatus.BAD_REQUEST);
        try {
            UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(
                    loginUser.getUserName(), loginUser.getPassword());
            Authentication authentication = authenticationManagerBuilder.getObject().authenticate(authenticationToken);
            SecurityContextHolder.getContext().setAuthentication(authentication);
            String jwt = jwtProvider.generateToken(authentication);
            JwtDto jwtDto = new JwtDto(jwt);
            return new ResponseEntity<>(jwtDto, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(new ResourceNotFoundException("Revise sus credenciales"),
                    HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/register")
    public ResponseEntity<Object> resgister(@Valid @RequestBody NewUser newUser, BindingResult bindingResult) {
        if (bindingResult.hasErrors())
            return new ResponseEntity<>(new ResourceNotFoundException("Revise los campos e intente nuevamente"),
                    HttpStatus.BAD_REQUEST);
        User user = new User(newUser.getUserName(), newUser.getEmail(),
                passwordEncoder.encode(newUser.getPassword()));
        Set<Role> roles = new HashSet<>();
        roles.add(roleService.getByRoleName(RoleList.ROLE_USER).get());
        if (newUser.getRoles().contains("admin"))
            roles.add(roleService.getByRoleName(RoleList.ROLE_ADMIN).get());
        user.setRoles(roles);
        userService.save(user);
        return new ResponseEntity<>(new ResourceNotFoundException("Registro exitoso! Inicie sesión"),
                HttpStatus.CREATED);
    }

}
