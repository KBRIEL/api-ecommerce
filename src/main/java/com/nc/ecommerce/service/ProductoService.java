package com.nc.ecommerce.service;


import com.nc.ecommerce.models.Producto;
import com.nc.ecommerce.models.Usuario;
import com.nc.ecommerce.models.dtos.ProductoDTO;
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
import java.util.Set;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.toList;

@Service
@Transactional
public class ProductoService {


    @Autowired
    private ProductoRepository accRepository;
    @Autowired
    private UsuarioRepository clRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;
    public ProductoService() {
    }
    public List<ProductoDTO> getAll(){
        return accRepository.findAll().stream().map(a -> new ProductoDTO(a)).collect(toList());
    }

    public ProductoDTO getAccount(Long id){

        return accRepository.findById(id).map(a-> new ProductoDTO(a)).orElse(null);
    }



    public ResponseEntity<Object> cargarProducto(Authentication authentication) {
        Usuario client = clRepository.findByNombre(authentication.getName());
        Set<Producto> productos = accRepository.findAll().stream().filter(account -> account.getUsuario_id() == client).collect(Collectors.toSet());

        if (productos.size() < 3) {
            int num=(int)(Math.floor(Math.random()* 99999999+1));
            String number= "VIN" + num;
            accRepository.save(new Producto(number, "", 0.0, client));
            return new ResponseEntity<>("successfully ", HttpStatus.CREATED);
        } else {
            return new ResponseEntity<>("exceeded limit", HttpStatus.FORBIDDEN);
        }

    }



}
