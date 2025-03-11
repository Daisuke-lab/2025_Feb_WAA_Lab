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
public class AuthUser implements UserDetails
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    String username;
    String password;

    @OneToOne(mappedBy = "auth_user")
    User user;

    @ManyToMany(fetch = FetchType.EAGER)
    List<Authority> authorities;


}
