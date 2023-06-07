package com.nc.ecommerce.controllers;


import com.nc.ecommerce.models.Usuario;
import com.nc.ecommerce.repositories.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;




@RestController
@RequestMapping("/api")
public class AppController {

    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private UsuarioRepository usRepository;

    @RequestMapping(path = "/register", method = RequestMethod.POST)
    public ResponseEntity<Object> register(
            @RequestParam String nombre,
            @RequestParam String email, @RequestParam String password) {

        if (nombre.isEmpty() ||  email.isEmpty() || password.isEmpty()) {
            return new ResponseEntity<>("Missing data", HttpStatus.FORBIDDEN);
        }

        if (usRepository.findByEmail(email) !=  null) {
            return new ResponseEntity<>("Name already in use", HttpStatus.FORBIDDEN);
        }

        usRepository.save(new Usuario(nombre, email, passwordEncoder.encode(password),false));
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

}