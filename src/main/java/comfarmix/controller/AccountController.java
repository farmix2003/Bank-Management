package comfarmix.controller;

import comfarmix.dto.AccountDTO;
import comfarmix.service.AccountService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/api/accounts")
public class AccountController {

    private AccountService accountService;

    public  AccountController(AccountService accountService){
        this.accountService = accountService;
    }

    @PostMapping("/add-user")
    public ResponseEntity<AccountDTO> addUser(@RequestBody AccountDTO account) {
        return new ResponseEntity<>(accountService.createAccount(account), HttpStatus.CREATED);
    }
    @GetMapping("/get-user/{id}")
    public ResponseEntity<AccountDTO> getUser(@PathVariable Long id) {
        return new ResponseEntity<>(accountService.getAccountById(id), HttpStatus.OK);
    }
}
