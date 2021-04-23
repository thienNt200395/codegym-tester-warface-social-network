package c1020g1.social_network.controller;

import c1020g1.social_network.config.JwtTokenUtil;
import c1020g1.social_network.model.account.*;
import c1020g1.social_network.service.account_service.implement.JwtAccountDetailService;
import com.google.api.client.googleapis.auth.oauth2.GoogleIdToken;
import com.google.api.client.googleapis.auth.oauth2.GoogleIdTokenVerifier;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.jackson2.JacksonFactory;
import com.google.api.client.util.Value;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.social.facebook.api.Facebook;
import org.springframework.social.facebook.api.User;
import org.springframework.social.facebook.api.impl.FacebookTemplate;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.Collections;

@RestController
@CrossOrigin("http://localhost:4200")
public class JwtAuthenticationController {

    @Value("${google.clienId}")
    String googleClientId;


    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @Autowired
    private JwtAccountDetailService jwtAccountDetailService;


    @GetMapping(value = "/error-page/{accountName}")
    public ResponseEntity<?> test(@PathVariable("accountName") String accountName) {
        c1020g1.social_network.model.User user = jwtAccountDetailService.getAccount(accountName).getUser();
        return ResponseEntity.ok(user);
    }

    @PostMapping(value = "/login")
    public ResponseEntity<?> createAuthenticationToken(@RequestBody JwtRequest jwtRequest) {
        JwtResponse jwtResponse = login(jwtRequest);
        return ResponseEntity.ok(jwtResponse);
    }

    private JwtResponse login(JwtRequest jwtRequest){
        try {
            authenticate(jwtRequest.getAccountName(), jwtRequest.getPassword());
        } catch (Exception e) {
            return new JwtResponse(e.getMessage());
        }

        final UserDetails userDetails = jwtAccountDetailService
                .loadUserByUsername(jwtRequest.getAccountName());

        final String token = jwtTokenUtil.generateToken(userDetails);

        return new JwtResponse(token);
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

    @PostMapping("oauth/google")
    public ResponseEntity<?> google(@RequestBody SocialResponse jwtResponseSocial) throws IOException {
        final NetHttpTransport netHttpTransport = new NetHttpTransport();
        final JacksonFactory jacksonFactory = JacksonFactory.getDefaultInstance();
        GoogleIdTokenVerifier.Builder builder =
                new GoogleIdTokenVerifier.Builder(netHttpTransport, jacksonFactory)
                        .setAudience(Collections.singletonList(googleClientId));
        final GoogleIdToken googleIdToken = GoogleIdToken.parse(builder.getJsonFactory(),jwtResponseSocial.getToken());
        final GoogleIdToken.Payload payload =  googleIdToken.getPayload();
        Account account = jwtAccountDetailService.getAccount(payload.getEmail());
        if (account == null){
            account = jwtAccountDetailService.saveSocial(payload.getEmail());
        }
        JwtRequest jwtRequest = new JwtRequest(account.getAccountName(),account.getPassword());
        JwtResponse jwtResponse = loginSocial(jwtRequest);
        return ResponseEntity.ok(jwtResponse);
    }

    @PostMapping("oauth/facebook")
    public ResponseEntity<?> facebook(@RequestBody SocialResponse jwtResponseSocial) throws IOException {
        Facebook facebook = new FacebookTemplate(jwtResponseSocial.getToken());

        final String[] fields = {"email","picture"};
        User user = facebook.fetchObject("me",User.class,fields);
        Account account = jwtAccountDetailService.getAccount(user.getEmail());
        if (account == null){
            account = jwtAccountDetailService.saveSocial(user.getEmail());
        }
        JwtRequest jwtRequest = new JwtRequest(account.getAccountName(),account.getPassword());
        JwtResponse jwtResponse = loginSocial(jwtRequest);
        return ResponseEntity.ok(jwtResponse);
    }

    private JwtResponse loginSocial(JwtRequest jwtRequest){
        final UserDetails userDetails = jwtAccountDetailService
                .loadUserByUsername(jwtRequest.getAccountName());

        final String token = jwtTokenUtil.generateToken(userDetails);

        return new JwtResponse(token);
    }
}
