package miu.edu.demo.services;

import miu.edu.demo.entities.AuthUser;
import miu.edu.demo.repositories.AuthRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;


public class AuthService implements UserDetailsService {

    @Autowired
    AuthRepository authRepository;

    @Autowired
    AuthenticationManager authenticationManager;


    @Override
    public AuthUser loadUserByUsername(String username) throws UsernameNotFoundException {
        return authRepository.findByUsername(username);
    }

    public AuthUser login(AuthUser authUser) {
        Authentication result = null;
        try {
            result = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(authUser.getUsername(), authUser.getPassword())
            );
        } catch (BadCredentialsException e) {
            throw new BadCredentialsException(e.getMessage());
        }

        final UserDetails userDetails = userDetailsService.loadUserByUsername(result.getName());

        final String accessToken = jwtUtil.generateToken(userDetails);
        final String refreshToken = jwtUtil.generateRefreshToken(loginRequest.getEmail());
        return new LoginResponse(accessToken, refreshToken);
    }
}
