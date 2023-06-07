package com.nc.ecommerce;

import com.nc.ecommerce.models.*;
import com.nc.ecommerce.repositories.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;

@SpringBootApplication
public class EcommerceApplication {

	@Autowired
	private PasswordEncoder passwordEncoder;



	@Bean
	public CommandLineRunner initData(UsuarioRepository clRepository, ProductoRepository proRepository) {
		return (args) -> {


			Usuario cesar = new Usuario("Cesar",  "cesar@nc.com", passwordEncoder.encode("cesarpass"),true);
			Usuario eliz = new Usuario("Eliz",  "eliz@nc.com", passwordEncoder.encode("elizpass"),false);
			Usuario damian = new Usuario("Damian", "damian@nc.com", passwordEncoder.encode("damianpass"),false);
			clRepository.save(cesar);
			clRepository.save(eliz);
			clRepository.save(damian);




			Producto p1 = new Producto("Manzanas", "./img/Manzanas.jpg",  500.0, cesar);
			Producto p2 = new Producto("Peras", "./img/Pera.jpg",  300.0, cesar);
			Producto p3 = new Producto("Espinaca", "./img/Espinaca.jpg",  100.0, cesar);
			Producto p4 = new Producto("sandia", "./img/Sandia.jpg",  50.0, cesar);
			Producto p5 = new Producto("Mango", "./img/Mango.jpg",  100.0, cesar);
			Producto p6 = new Producto("Papas", "./img/Papas.jpg",  500.0, cesar);
			proRepository.save(p1);
			proRepository.save(p2);
			proRepository.save(p3);
			proRepository.save(p4);
			proRepository.save(p5);
			proRepository.save(p6);






			System.out.println(" -- carga completa de datos --");


		};

	}

	public static void main(String[] args) {
		SpringApplication.run(EcommerceApplication.class, args);
	}



}
