package comfarmix.service.impl;

import comfarmix.dto.AccountDTO;
import comfarmix.entity.Account;
import comfarmix.mapper.AccountMapper;
import comfarmix.repository.AccountRepo;
import comfarmix.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collector;
import java.util.stream.Collectors;

@Service
public class AccountServiceImpl implements AccountService {

    @Autowired
    private AccountRepo accountRepo;

    @Override
    public AccountDTO createAccount(AccountDTO account) {
        Account newAccount = AccountMapper.mapToAccount(account);
        Account savedAccount = accountRepo.save(newAccount);
        return AccountMapper.mapToAccountDTO(savedAccount);
    }

    @Override
    public AccountDTO getAccountById(Long id) {
        Account account = accountRepo.findById(id).orElseThrow(() -> new RuntimeException("Account not found"));
        return AccountMapper.mapToAccountDTO(account);
    }

    @Override
    public AccountDTO deposit(Long id, Double amount) {
        Account account = accountRepo.findById(id).orElseThrow(() -> new RuntimeException("Account not found"));
        if(amount <= 0){
            throw new RuntimeException("Please Enter correct amount (>0)");
        }
        double  total = account.getBalance() + amount;
        account.setBalance(total);
        Account savedAccount = accountRepo.save(account);
        return AccountMapper.mapToAccountDTO(savedAccount);
    }

    @Override
    public AccountDTO withdraw(Long id, Double amount) {
        Account account = accountRepo.findById(id).orElseThrow(() -> new RuntimeException("Account not found"));
        if(account.getBalance() < amount){
            throw new RuntimeException("You do not have enough money to withdraw");
        }
        if(amount <= 0){
            throw new RuntimeException("Please Enter correct amount (>0)");
        }
        double  total = account.getBalance() - amount;
        account.setBalance(total);
        Account savedAccount = accountRepo.save(account);
        return AccountMapper.mapToAccountDTO(savedAccount);
    }

    @Override
    public List<AccountDTO> getAllAccounts() {
        List<Account> accounts = accountRepo.findAll();
        return accounts.stream().map(AccountMapper::mapToAccountDTO).collect(Collectors.toList());
    }

    @Override
    public void deleteAccount(Long id) {
        Account account = accountRepo.findById(id).orElseThrow(() -> new RuntimeException("Account not found"));
        accountRepo.delete(account);
    }


}
