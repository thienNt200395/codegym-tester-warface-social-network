package c1020g1.social_network.service.impl;

import c1020g1.social_network.repository.AccountRepository;
import c1020g1.social_network.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AccountServiceImpl implements AccountService {
    @Autowired
    private AccountRepository accountRepository;

    @Override
    public void changePassword(Integer accountId, String newPassword, String password, String confirmPassword) {
        accountRepository.changePassword( accountId,newPassword );
    }
}
