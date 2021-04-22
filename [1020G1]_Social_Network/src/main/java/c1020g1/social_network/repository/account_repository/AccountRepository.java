package c1020g1.social_network.repository.account_repository;

import c1020g1.social_network.model.account.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AccountRepository extends JpaRepository<Account, Integer> {

    Account findAccountByAccountName(String accountName);
}
