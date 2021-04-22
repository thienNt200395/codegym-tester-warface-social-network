package c1020g1.social_network.controller;

import c1020g1.social_network.config.JwtTokenUtil;
import c1020g1.social_network.model.account.AccountDTO;
import c1020g1.social_network.model.account.JwtRequest;
import c1020g1.social_network.model.account.JwtResponse;
import c1020g1.social_network.service.account_service.implement.JwtAccountDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin("http://localhost:4200")
public class JwtAuthenticationController {
    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @Autowired
    private JwtAccountDetailService jwtAccountDetailService;

//    @RequestMapping(value = "/register", method = RequestMethod.POST)
//    public ResponseEntity<?> saveUser(@RequestBody AccountDTO accountDTO) throws Exception {
//        return ResponseEntity.ok(jwtAccountDetailService.save(accountDTO));
//    }

    @GetMapping(value = "/error-page")
    public ResponseEntity<?> test() {
        return ResponseEntity.ok(new AccountDTO());
    }

    @PostMapping(value = "/login")
    public ResponseEntity<?> createAuthenticationToken(@RequestBody JwtRequest authenticationRequest) {

        try {
            authenticate(authenticationRequest.getAccountName(), authenticationRequest.getPassword());
        } catch (Exception e) {
            return ResponseEntity.ok(new JwtResponse(e.getMessage()));
        }

        final UserDetails userDetails = jwtAccountDetailService
                .loadUserByUsername(authenticationRequest.getAccountName());

        final String token = jwtTokenUtil.generateToken(userDetails);

        return ResponseEntity.ok(new JwtResponse(token));
    }


    private void authenticate(String accountName, String password) throws Exception {
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(accountName, password));
        } catch (DisabledException e) {
            throw new Exception("USER_DISABLED", e);
        } catch (BadCredentialsException e) {
            throw new Exception("INVALID_CREDENTIALS", e);
        }
    }
}
