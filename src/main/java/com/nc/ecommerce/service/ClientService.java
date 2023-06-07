package com.nc.ecommerce.service;

import com.nc.ecommerce.models.Procuctos;
import com.nc.ecommerce.models.Usuario;
import com.nc.ecommerce.models.dtos.UsuarioDTO;
import com.nc.ecommerce.repositories.AccountRepository;
import com.nc.ecommerce.repositories.ClientRepository;
import com.nc.ecommerce.repositories.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;


@Service
@Transactional
public class ClientService {

    @Autowired
    private TransactionRepository txRepository;
    @Autowired
    private ClientRepository clRepository;
    @Autowired
    private AccountRepository accRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;
    public ClientService() {
    }

    public UsuarioDTO getClientE(String email) {
        return new UsuarioDTO(clRepository.findByEmail(email));
    }
    public UsuarioDTO getClient(Long id) {
        return new UsuarioDTO(clRepository.findById(id).get());
    }




    public ResponseEntity<Object> register( String firstName, String lastName, String email, String password) {

        if (firstName.isEmpty() || lastName.isEmpty() || email.isEmpty() || password.isEmpty()) {
            return new ResponseEntity<>("Missing data", HttpStatus.FORBIDDEN);
        }

        if (clRepository.findByEmail(email) != null) {
            return new ResponseEntity<>("Name in use", HttpStatus.FORBIDDEN);
        }else{
            Usuario client = new Usuario(firstName, lastName, email, passwordEncoder.encode(password));
            clRepository.save(client);

            int num=(int)(Math.floor(Math.random()* 99999999+1));
            String number= "VIN" + num;
            Procuctos account = new Procuctos(number, LocalDateTime.now(), 0, client);
            accRepository.save(account);
            return new ResponseEntity<>(HttpStatus.CREATED);
        }

    }

    public List<UsuarioDTO> getClients() {

            return clRepository.findAll().stream().map(cl -> new UsuarioDTO(cl)).collect(Collectors.toList());


    }

    public UsuarioDTO getClientCurrent(Authentication authentication) {
        return new UsuarioDTO(clRepository.findByEmail(authentication.getName()));
    }



}
