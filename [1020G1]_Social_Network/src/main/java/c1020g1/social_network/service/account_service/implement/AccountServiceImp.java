package c1020g1.social_network.service.account_service.implement;

import c1020g1.social_network.model.account.Account;
import c1020g1.social_network.repository.account_repository.AccountRepository;
import c1020g1.social_network.service.account_service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;

public class AccountServiceImp implements AccountService {

    @Autowired
    AccountRepository accountRepository;

    @Override
    public Account findByAccountName(String accountName) {
       return accountRepository.findAccountByAccountName(accountName);
    }
}
