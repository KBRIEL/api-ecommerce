package com.nc.ecommerce.service;

import com.nc.ecommerce.models.Producto;
import com.nc.ecommerce.models.Usuario;
import com.nc.ecommerce.models.dtos.UsuarioDTO;
import com.nc.ecommerce.repositories.ProductoRepository;
import com.nc.ecommerce.repositories.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;


@Service
@Transactional
public class UsuarioService {


    @Autowired
    private UsuarioRepository clRepository;
    @Autowired
    private ProductoRepository proRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;
    public UsuarioService() {
    }

    public UsuarioDTO getClientE(String email) {
        return new UsuarioDTO(clRepository.findByEmail(email));
    }
    public UsuarioDTO getClient(Long id) {
        return new UsuarioDTO(clRepository.findById(id).get());
    }




    public ResponseEntity<Object> register(/* String firstName, String lastName, */String nombre,String email, String password) {

        if ( nombre.isEmpty() || email.isEmpty() || password.isEmpty()) {
            return new ResponseEntity<>("Missing data", HttpStatus.FORBIDDEN);
        }

        if (clRepository.findByEmail(email) != null) {
            return new ResponseEntity<>("Name in use", HttpStatus.FORBIDDEN);
        }else{
            Usuario client = new Usuario(nombre, email, passwordEncoder.encode(password),false);
            clRepository.save(client);


            Producto procucto = new Producto("","",  0.0, client);
            proRepository.save(procucto);
            return new ResponseEntity<>(HttpStatus.CREATED);
        }

    }

    public List<UsuarioDTO> getUsuarios() {

            return clRepository.findAll().stream().map(cl -> new UsuarioDTO(cl)).collect(Collectors.toList());


    }

    public UsuarioDTO getClientCurrent(Authentication authentication) {
        return new UsuarioDTO(clRepository.findByEmail(authentication.getName()));
    }



}
