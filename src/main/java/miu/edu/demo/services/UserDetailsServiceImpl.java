package miu.edu.demo.services;

import miu.edu.demo.entities.User;
import miu.edu.demo.entities.UserDetailsModel;
import miu.edu.demo.repositories.UserDetailsRepository;
import miu.edu.demo.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

public class UserDetailsServiceImpl  implements UserDetailsService {

    @Autowired
    UserDetailsRepository userDetailsRepository;


    @Override
    public UserDetailsModel loadUserByUsername(String username) throws UsernameNotFoundException {
        return userDetailsRepository.findByUsername(username);
    }
}
