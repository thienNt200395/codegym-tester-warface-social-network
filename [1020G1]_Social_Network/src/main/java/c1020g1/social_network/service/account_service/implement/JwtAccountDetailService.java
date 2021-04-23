package c1020g1.social_network.service.account_service.implement;

import c1020g1.social_network.model.account.Account;
import c1020g1.social_network.model.account.AccountDTO;
import c1020g1.social_network.repository.account_repository.AccountRepository;
import c1020g1.social_network.sercurity.AccountPrincipleFactory;
import com.google.api.client.util.Value;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;

@Service
@Transactional
public class JwtAccountDetailService implements UserDetailsService {

    String secretPwt = "123456";

    @Autowired
    AccountRepository accountRepository;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Override
    public UserDetails loadUserByUsername(String accountName) throws UsernameNotFoundException {
        Account account = accountRepository.findAccountByAccountName(accountName);
        if(account == null) {
            throw new UsernameNotFoundException("Account not found with account name: " + accountName);
        }
        return AccountPrincipleFactory.build(account);
    }

    public Account saveSocial(String accountName) {
        Account account = new Account();
        account.setAccountName(accountName);
        account.setPassword(passwordEncoder.encode(secretPwt));
        return accountRepository.save(account);
    }

    public Account getAccount(String accountName){
        return accountRepository.findAccountByAccountName(accountName);
    }
}
