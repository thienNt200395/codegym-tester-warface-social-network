package c1020g1.social_network.controller;

import c1020g1.social_network.model.Account;
import c1020g1.social_network.model.User;
import c1020g1.social_network.service.AccountService;
import c1020g1.social_network.service.UserService;
import c1020g1.social_network.service.impl.EmailSenderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin("*")
public class AccountController {
    @Autowired
    private AccountService accountService;

    @Autowired
    private EmailSenderService emailSenderService;

    @Autowired
    private UserService userService;

    /**
     * method: sent email to mail of user
     * author: HanTH
     * @param id
     * @param code
     */
    @GetMapping("/account/{idAccount}/changePassword")
    public void sendMailConfirmChangePassword(@PathVariable("idAccount") Integer id,
                                              @RequestParam("code") Integer code) {
        User user = userService.getUserById( id );
        SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
        simpleMailMessage.setTo( user.getEmail() );
        simpleMailMessage.setSubject( "Confirm Change Password" );
        simpleMailMessage.setFrom( "huuhan2507@gmail.com" );
        simpleMailMessage.setText( "Your Code: " + code );
        emailSenderService.sendEmail( simpleMailMessage );
    }

    /**
     * method: change password of user through account.
     * author: HanTH
     * @param accountName
     * @param oldPassword
     * @param newPassword
     * @param confirmPassword
     */
    @PutMapping("/account/{accountName}/changePassword")
    public void ChangePassword(@PathVariable("accountName") String accountName,
                               @RequestParam("oldPassword") String oldPassword,
                               @RequestParam("newPassword") String newPassword,
                               @RequestParam("confirmPassword") String confirmPassword) {
        Account account = accountService.findAccountById( accountName );
        if (accountService.checkChangePassword( account, oldPassword, newPassword, confirmPassword )){
            accountService.changePassword( account, oldPassword, newPassword, confirmPassword );
        }
    }
}
