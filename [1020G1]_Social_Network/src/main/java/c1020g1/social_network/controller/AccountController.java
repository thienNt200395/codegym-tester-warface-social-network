package c1020g1.social_network.controller;

import c1020g1.social_network.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin("*")
public class AccountController {
    @Autowired
    private AccountService accountService;

    @PutMapping("/account/{idAccount}/changePassword/{newPassword}")
    public void changePassword(@PathVariable("idAccount") Integer id, @PathVariable("newPassword") String newPassword,
                               @RequestParam("password") String password, @RequestParam("confirmPassword") String confirmPassword) {
        accountService.changePassword( id, password, newPassword, confirmPassword );
    }
}
