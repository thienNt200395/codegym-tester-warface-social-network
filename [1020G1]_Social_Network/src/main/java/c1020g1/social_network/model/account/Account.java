package c1020g1.social_network.model.account;

import c1020g1.social_network.model.User;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "account")
public class Account {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "account_id")
    private Integer accountId;
    @Column(name = "account_name",nullable = false, unique = true)
    private String accountName;
    @Column(name = "password")
    private String password;

    @OneToOne(mappedBy = "account")
    private User user;

    @OneToMany(mappedBy = "account")
    private List<ReferentAccount> referentAccounts;

    public Integer getAccountId() {
        return accountId;
    }

    public void setAccountId(Integer accountId) {
        this.accountId = accountId;
    }

    public String getAccountName() {
        return accountName;
    }

    public void setAccountName(String accountName) {
        this.accountName = accountName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public List<ReferentAccount> getReferentAccounts() {
        return referentAccounts;
    }

    public void setReferentAccounts(List<ReferentAccount> referentAccounts) {
        this.referentAccounts = referentAccounts;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
