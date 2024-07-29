package comfarmix.service;

import comfarmix.dto.AccountDTO;

public interface AccountService {
    AccountDTO createAccount(AccountDTO account);
    AccountDTO getAccountById(Long id);

}
