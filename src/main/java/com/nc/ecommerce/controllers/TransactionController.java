package com.nc.ecommerce.controllers;



import com.nc.ecommerce.models.Procuctos;
import com.nc.ecommerce.models.Usuario;
import com.nc.ecommerce.repositories.AccountRepository;
import com.nc.ecommerce.repositories.ClientRepository;
import com.nc.ecommerce.repositories.TransactionRepository;
import com.nc.ecommerce.service.ClientService;
import com.nc.ecommerce.service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;



@Controller
@RequestMapping("/api")
public class TransactionController {
    @Autowired
    private TransactionRepository txRepository;
    @Autowired
    private AccountRepository accRepository;
    @Autowired
    private ClientRepository clRepository;
    @Autowired
    private ClientService clService;
    @Autowired
    private TransactionService txService;



    @Transactional
    @PostMapping("/transactions")
    public ResponseEntity<Object> createTransaction(
            Authentication auth, @RequestParam String description,
            @RequestParam Double amount, @RequestParam String originAccount, @RequestParam String destinationAccount) {
       Usuario client = clRepository.findByEmail(auth.getName());
       Procuctos origin= accRepository.findByNumber(originAccount);
       Procuctos destination= accRepository.findByNumber(destinationAccount);

        return  this.txService.createTransaction(client,description,amount,origin, destination);





      // new ResponseEntity<>("Transaction success", HttpStatus.CREATED);




    }

}
