package com.nc.ecommerce;

import com.nc.ecommerce.models.*;
import com.nc.ecommerce.repositories.*;
import com.nc.ecommerce.service.TransactionService;
import com.nc.ecommerce.models.*;
import com.nc.ecommerce.repositories.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.time.LocalDateTime;
import java.util.List;

@SpringBootApplication
public class EcommerceApplication {

	@Autowired
	private PasswordEncoder passwordEncoder;



	@Bean
	public CommandLineRunner initData(ClientRepository clRepository, AccountRepository accRepository,
									  TransactionRepository trxRepository, LoanRepository loRepository,
									  ClientLoanRepository clLRepository, CardRepository cdRepository, TransactionService ts) {
		return (args) -> {


			Usuario clientM = new Usuario("Cesar", "Morel", "cesar@nc.com", passwordEncoder.encode("cesarpass"));
			Usuario clientD = new Usuario("Eliz", "Larusso", "eliz@nc.com", passwordEncoder.encode("elizpass"));
			Usuario clientAD = new Usuario("Damian", "Perez", "damian@nc.com", passwordEncoder.encode("damianpass"));
			clRepository.save(clientM);
			clRepository.save(clientD);
			clRepository.save(clientAD);




			Procuctos cM1 = new Procuctos("VIN001", LocalDateTime.now(), 5000, clientM);
			Procuctos cM2 = new Procuctos("VIN002", LocalDateTime.now().plusDays(1), 7500, clientM);
			Procuctos cD1 = new Procuctos("VIN003", LocalDateTime.now(), 3000, clientD);
			accRepository.save(cM1);
			accRepository.save(cM2);
			accRepository.save(cD1);


			Transaction trx1 = new Transaction(TransactionType.CREDITO, 100, "compra1 ", LocalDateTime.now(),cM1);
			Transaction trx2 = new Transaction(TransactionType.DEBITO, 300, "compra2 ", LocalDateTime.now().plusDays(-1),cM2);
			Transaction trx3 = new Transaction(TransactionType.DEBITO, 200, "compra3 ", LocalDateTime.now().plusDays(-2),cM1);
			Transaction trx4 = new Transaction(TransactionType.DEBITO, 400, "compra4 ", LocalDateTime.now(),cD1);
			trxRepository.save(trx1);
			trxRepository.save(trx2);
			trxRepository.save(trx3);
			trxRepository.save(trx4);

			List<Integer> cuotas1 = List.of(12, 24, 36, 48, 60);
			List<Integer> cuotas2 = List.of(6, 12, 24);
			List<Integer> cuotas3 = List.of(6, 12, 24,36);

			Loan l1 = new Loan("credito1",500000,cuotas1);
			Loan l2 = new Loan("credito2",100000,cuotas2);
			Loan l3 = new Loan("credito3",300000,cuotas3);

			loRepository.save(l1);
			loRepository.save(l2);
			loRepository.save(l3);

			ClientLoan clL1 = new ClientLoan(400000,60,clientM,l1);
			ClientLoan clL2 = new ClientLoan(50000,12,clientM,l3);
			ClientLoan clL3 = new ClientLoan(100000,24,clientM,l2);
			ClientLoan clL4 = new ClientLoan(200000,36,clientM,l3);
			clLRepository.save(clL1);
			clLRepository.save(clL2);
			clLRepository.save(clL3);
			clLRepository.save(clL4);

			String nameM= clientM.getFirstName() + clientM.getLastName();
			String nameD= clientD.getFirstName() + clientD.getLastName();

			Card card1 = new Card(clientM, CardColor.GOLD, CardType.DEBIT,  "1111-1111-1111-1111", 111, LocalDateTime.now(), LocalDateTime.now().plusYears(5));
			Card card2 = new Card(clientM, CardColor.TiTANIUM, CardType.CREDIT, "2222-2222-2222-2222", 222, LocalDateTime.now(), LocalDateTime.now().plusYears(5));
			Card card3 = new Card(clientD, CardColor.SILVER, CardType.CREDIT, "3333-3333-3333-3333", 333, LocalDateTime.now(), LocalDateTime.now().plusYears(5));
			cdRepository.save(card1);
			cdRepository.save(card2);
			cdRepository.save(card3);
/*
			ts.createTransaction(au,"hla", 30.0,
					"VIN001", "VIN002");

			 */




			System.out.println(" -- carga completa de datos --");


		};

	}

	public static void main(String[] args) {
		SpringApplication.run(EcommerceApplication.class, args);
	}



}
