package c1020g1.social_network.service.impl;

import c1020g1.social_network.model.Account;
import c1020g1.social_network.repository.AccountRepository;
import c1020g1.social_network.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AccountServiceImpl implements AccountService {
    @Autowired
    private AccountRepository accountRepository;

    @Override
    public void changePassword(Account account, String oldPassword, String newPassword, String confirmPassword) {
        accountRepository.changePassword( account.getAccountId(), new BCryptPasswordEncoder().encode( newPassword ) );
    }

    @Override
    public Account findAccountById(String accountName) {
        return accountRepository.findAccountByAccountName( accountName );
    }

    @Override
    public boolean checkChangePassword(Account account, String oldPassword, String newPassword, String confirmPassword) {
        BCryptPasswordEncoder cryptPasswordEncoder = new BCryptPasswordEncoder();
        if (!cryptPasswordEncoder.matches( oldPassword, account.getPassword() )) {
            return !oldPassword.equals( newPassword ) && newPassword.equals( confirmPassword );
        }
        return false;
    }
}
