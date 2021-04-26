package c1020g1.social_network.service;

public interface AccountService {
    void changePassword(Integer accountId, String newPassword, String password , String confirmPassword);
}
