package miu.edu.demo.entities;

import lombok.Data;

import jakarta.persistence.*;
import org.springframework.security.core.GrantedAuthority;

import java.util.List;

@Entity
@Data
public class Authority implements GrantedAuthority {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String role;

    @Override
    public String getAuthority() {
        return role;
    }
}
