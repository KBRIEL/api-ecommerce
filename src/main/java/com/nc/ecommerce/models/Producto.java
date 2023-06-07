package com.nc.ecommerce.models;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@Entity
public class Producto {


    @Id
   @GeneratedValue(strategy = GenerationType.AUTO)
  @GenericGenerator(name = "native", strategy = "native")
    private Long producto_id;
    @Column
    private String title;
    @Column
    private String thumbnailUrl;
    @Column
    private Double precio;



    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="client_id")
    private Usuario usuario_id;





    public Producto() {
  }



    public Producto(String title, String thumbnailUrl, Double precio, Usuario usuario_id) {
        this.title = title;
        this.thumbnailUrl = thumbnailUrl;
        this.precio = precio;
        this.usuario_id = usuario_id;

    }

    public Long getProducto_id() {
        return producto_id;
    }

    public void setProducto_id(Long producto_id) {
        this.producto_id = producto_id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getThumbnailUrl() {
        return thumbnailUrl;
    }

    public void setThumbnailUrl(String thumbnailUrl) {
        this.thumbnailUrl = thumbnailUrl;
    }

    public Double getPrecio() {
        return precio;
    }

    public void setPrecio(Double precio) {
        this.precio = precio;
    }

    public Usuario getUsuario_id() {
        return usuario_id;
    }

    public void setUsuario_id(Usuario usuario_id) {
        this.usuario_id = usuario_id;
    }
}
