package comfarmix.mapper;

import comfarmix.dto.AccountDTO;
import comfarmix.entity.Account;

public class AccountMapper {

    public static Account mapToAccount(AccountDTO accountDTO) {
        return new Account(
                accountDTO.getId(),
                accountDTO.getAccountHolderName(),
                accountDTO.getBalance()
        );
    }

    public static AccountDTO mapToAccountDTO(Account account) {
        return new AccountDTO(
                account.getId(),
                account.getAccountHolderName(),
                account.getBalance()
        );
    }
}
