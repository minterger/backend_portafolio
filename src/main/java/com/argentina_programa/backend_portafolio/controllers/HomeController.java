package com.argentina_programa.backend_portafolio.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HomeController {
    @GetMapping("/")
    public ResponseEntity<String> sendHello() {
        return ResponseEntity.ok("Hola Argentina Programa (Backend Portafolio -> Oscar Leonel Menci)");
    }
}
