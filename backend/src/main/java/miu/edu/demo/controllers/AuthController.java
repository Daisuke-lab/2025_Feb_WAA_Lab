package miu.edu.demo.controllers;

import miu.edu.demo.entities.AuthUser;
import miu.edu.demo.entities.LoginResponse;
import miu.edu.demo.entities.RefreshToken;
import miu.edu.demo.services.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("auth")
public class AuthController {

    @Autowired
    private AuthService authService;


    @PostMapping(path = "login")
    public ResponseEntity<?> login(@RequestBody AuthUser authUser) {
        var loginResponse = authService.login(authUser);
        return new ResponseEntity<LoginResponse>(
                loginResponse, HttpStatus.OK);
    }

    @PostMapping("/refreshToken")
    public LoginResponse refreshToken(@RequestBody RefreshToken refreshToken) {
        return authService.refreshToken(refreshToken);
    }
}
