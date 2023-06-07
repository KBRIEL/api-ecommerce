package com.nc.ecommerce.models.dtos;

import com.nc.ecommerce.models.Producto;

import java.time.LocalDateTime;
import java.util.Set;

public class ProductoDTO {


    private Long producto_id;
    private String title;

    private String thumbnailUrl;
    private Double precio;


    public ProductoDTO(Producto pro) {
        this.producto_id = pro.getProducto_id();
        this.title = pro.getTitle();
        this.thumbnailUrl = pro.getThumbnailUrl();
        this.precio = pro.getPrecio();
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
}
