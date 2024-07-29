package comfarmix.service.impl;

import comfarmix.dto.AccountDTO;
import comfarmix.entity.Account;
import comfarmix.mapper.AccountMapper;
import comfarmix.repository.AccountRepo;
import comfarmix.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

}
