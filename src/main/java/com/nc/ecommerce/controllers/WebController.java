package com.nc.ecommerce.controllers;


import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;


@RestController
@RequestMapping("/web")
public class WebController {

    @RequestMapping("/acount.html")
    public ModelAndView getAcc() {
        ModelAndView mv= new ModelAndView();
        mv.setViewName("account.html");
        return mv;
    }

    /*

    @Autowired
    private AccountRepository accRepository;

    @RequestMapping("/accounts")
    public List<AccountDTO> getAll(){
        return accRepository.findAll().stream().map(a -> new AccountDTO(a)).collect(toList());
    }

    @RequestMapping("/accounts/{id}")
    public AccountDTO getAccount(@PathVariable Long id){
        return accRepository.findById(id).map(a-> new AccountDTO(a)).orElse(null);
    }
*/
}