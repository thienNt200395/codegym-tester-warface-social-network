package c1020g1.social_network.service.account_service.implement;

import c1020g1.social_network.model.account.Account;
import c1020g1.social_network.repository.account_repository.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class JwtAccountDetailService implements UserDetailsService {

    @Autowired
    AccountRepository accountRepository;

//    @Autowired
//    PasswordEncoder passwordEncoder;

    @Override
    public UserDetails loadUserByUsername(String accountName) throws UsernameNotFoundException {
        Account account = accountRepository.findAccountByAccountName(accountName);
        if(account == null) {
            throw new UsernameNotFoundException("Account not found with account name: " + accountName);
        }
        return new org.springframework.security.core.userdetails.User(account.getAccountName(), account.getPassword(),
                new ArrayList<>());
    }

//    public Account save(AccountDTO accountDTO) {
//        Account account = new Account();
//        account.setAccountName(accountDTO.getAccountName());
//        account.setPassword(passwordEncoder.encode(accountDTO.getPassword()));
//        return accountRepository.save(account);
//    }
}
