package com.nc.ecommerce.controllers;


import com.nc.ecommerce.models.dtos.UsuarioDTO;
import com.nc.ecommerce.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import java.util.List;



@RestController
@RequestMapping("/api")
public class UsuarioController {

    @Autowired
    private UsuarioService clService;


    @GetMapping("/clientes")
    public List<UsuarioDTO> getClients() throws Exception {
        try{
         //   return clRepository.findAll().stream().map(cl -> new ClientDTO(cl)).collect(Collectors.toList());
            return clService.getUsuarios();
        }catch (Exception e){
            throw new Exception("no se encontro cliente");
        }

    }

        @RequestMapping("/clientes/{id}")
        public UsuarioDTO getClient(@PathVariable Long id) {
            return clService.getClient(id);
        }

    @RequestMapping("/clientes/email/{email}")
    public UsuarioDTO getClientE(@PathVariable String email) {
        return clService.getClientE(email);

    }


    @PostMapping("/clientes")
    public ResponseEntity<Object> register( @RequestParam String nombre,
                                           @RequestParam String email, @RequestParam String password) {

        return clService.register( nombre, email, password);

    }



    @RequestMapping("/clients/current")
    public UsuarioDTO getClientCurrent(Authentication authentication) {
      //  return new ClientDTO(clRepository.findByEmail(authentication.getName()));
        return clService.getClientCurrent(authentication);
    }
}
