package miu.edu.demo.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.List;

@RequiredArgsConstructor
@AllArgsConstructor
@Data
@Entity
public class UserDetailsModel implements UserDetails
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    String username;
    String password;

    @ManyToMany(fetch = FetchType.EAGER)
    List<Authority> authorities;


}
