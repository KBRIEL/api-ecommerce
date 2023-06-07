package com.nc.ecommerce.repositories;

import com.nc.ecommerce.models.Producto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource
public interface ProductoRepository extends JpaRepository<Producto, Long> {

    public Producto findByTitle(String title);
}