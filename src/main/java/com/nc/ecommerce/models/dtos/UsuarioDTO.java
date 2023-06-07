package com.nc.ecommerce.models.dtos;

import com.nc.ecommerce.models.Usuario;

import java.util.HashSet;
import java.util.Set;


import static java.util.stream.Collectors.toSet;

public class UsuarioDTO {





    private Long client_id;
    private String nombre;

    private String passwork;

    private String email;

    private boolean isAdmin;
    private Set<ProductoDTO> productos = new HashSet<>();


    public UsuarioDTO(Usuario us){

        this.client_id= us.getClient_id();
        this.nombre =us.getNombre();
        this.passwork = us.getPassword();
        this.email= us.getEmail();
        this.isAdmin= us.getAdmin();
        this.productos = us.getProductos().stream().map(p ->new ProductoDTO(p)).collect(toSet()) ;

    }

    public boolean isAdmin() {
        return isAdmin;
    }

    public void setAdmin(boolean admin) {
        isAdmin = admin;
    }

    public Long getClient_id() {
        return client_id;
    }

    public void setClient_id(Long client_id) {
        this.client_id = client_id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getPasswork() {
        return passwork;
    }

    public void setPasswork(String passwork) {
        this.passwork = passwork;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Set<ProductoDTO> getProductos() {
        return productos;
    }




}
