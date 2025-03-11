package miu.edu.demo.services;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import miu.edu.demo.entities.AuthUser;
import miu.edu.demo.entities.LoginResponse;
import miu.edu.demo.entities.RefreshToken;
import miu.edu.demo.repositories.AuthRepository;
import miu.edu.demo.utils.JWTUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class AuthService implements UserDetailsService {


    AuthRepository authRepository;
    AuthenticationManager authenticationManager;
    JWTUtil jwtUtil;
    PasswordEncoder passwordEncoder;



    @Override
    public AuthUser loadUserByUsername(String username) throws UsernameNotFoundException {
        return authRepository.findByUsername(username);
    }

    public LoginResponse signup(AuthUser authUser) {
        if(authRepository.existsByUsername(authUser.getUsername())){
            throw new  BadCredentialsException("Username is already in use");
        }
        String originalPassword = authUser.getPassword();
        authUser.setPassword(passwordEncoder.encode(authUser.getPassword()));

        authRepository.save(authUser);
        authUser.setPassword(originalPassword);
        return login(authUser);
    }

    public LoginResponse login(AuthUser authUser) {
        Authentication result = null;
        try {
            result = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(authUser.getUsername(), authUser.getPassword())
            );
        } catch (BadCredentialsException e) {
            throw new BadCredentialsException(e.getMessage());
        }

        final UserDetails userDetails = this.loadUserByUsername(result.getName());

        final String accessToken = jwtUtil.generateToken(userDetails);
        final String refreshToken = jwtUtil.generateRefreshToken(authUser.getUsername());
        return new LoginResponse(accessToken, refreshToken);
    }

    public LoginResponse refreshToken(RefreshToken refreshToken) {
        if (jwtUtil.isRefreshToken(refreshToken.getToken())) { //Added check for refresh token type
            final String accessToken = jwtUtil.doGenerateToken(jwtUtil.getSubject(refreshToken.getToken()));
            return new LoginResponse(accessToken, refreshToken.getToken());
        } else {
            return new LoginResponse(); // Or throw an exception
        }
    }
}
