package com.nc.ecommerce.models;


import org.hibernate.annotations.GenericGenerator;
import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
public class Usuario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @GenericGenerator(name = "native", strategy = "native")
    private Long client_id;
    @Column
    private String nombre;

    @Column
    private String email;
    @Column
    private String password;

    @Column
    private Boolean isAdmin;


    public void setPassword(String password) {
        this.password = password;
    }

    @OneToMany(mappedBy= "usuario_id", fetch=FetchType.EAGER)
   Set<Producto> productos = new HashSet<>();



    public Usuario() {

    }



    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }


    public void cargarProducto(Producto pro){
        pro.setUsuario_id(this);

        productos.add(pro);
    }

    public Set<Producto> getProductos() {
        return productos;
    }

    public Long getClient_id() {
        return client_id;
    }

    public Boolean getAdmin() {
        return isAdmin;
    }

    public void setAdmin(Boolean admin) {
        isAdmin = admin;
    }

    public Usuario(String nombre, String email, String password, boolean admin) {

        this.nombre = nombre;
        this.email = email;
        this.password = password;
        this.isAdmin = admin;

    }

    public String getPassword() {
        return password;
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

    public void setProductos(Set<Producto> productos) {
        this.productos = productos;
    }
}
