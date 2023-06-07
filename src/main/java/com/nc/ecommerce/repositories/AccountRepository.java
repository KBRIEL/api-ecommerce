package com.nc.ecommerce.repositories;

import com.nc.ecommerce.models.Procuctos;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource
public interface AccountRepository extends JpaRepository<Procuctos, Long> {

    public Procuctos findByNumber(String number);
}