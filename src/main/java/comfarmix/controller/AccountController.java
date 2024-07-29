package comfarmix.controller;

import comfarmix.dto.AccountDTO;
import comfarmix.service.AccountService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

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

    @PutMapping("/{id}/deposit")
    public ResponseEntity<AccountDTO> deposit(@PathVariable Long id, @RequestBody Map<String, Double> request) {
        double amount = request.get("amount");
        return new ResponseEntity<>(accountService.deposit(id, amount), HttpStatus.CREATED);
    }
    @PutMapping("/{id}/withdraw")
    public ResponseEntity<AccountDTO> withdraw(@PathVariable Long id, @RequestBody Map<String, Double> request) {
        double amount = request.get("amount");
        return new ResponseEntity<>(accountService.withdraw(id, amount), HttpStatus.OK);
    }
    @GetMapping
    public ResponseEntity<List<AccountDTO>> getAllAccounts() {
        return new ResponseEntity<>(accountService.getAllAccounts(), HttpStatus.OK);
    }
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteAccount(@PathVariable Long id) {
        accountService.deleteAccount(id);
        return ResponseEntity.ok("Account deleted successfully");
    }
}
