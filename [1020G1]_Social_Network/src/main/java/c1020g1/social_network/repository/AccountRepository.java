package c1020g1.social_network.repository;

import c1020g1.social_network.model.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface AccountRepository extends JpaRepository<Account, Integer> {
    @Transactional
    @Modifying
    @Query(value = "update account\n" + "set password = ?2\n" + "where account_id = ?1", nativeQuery = true)
    void changePassword(Integer accountId, String newPassword);

    @Query(value = "select * from account where account_name=?1", nativeQuery = true)
    Account findAccountByAccountName(String accountName);
}
