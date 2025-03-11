package miu.edu.demo.entities;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
@AllArgsConstructor
@Entity
public class Address {
    @OneToOne
    User user;

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    long id;
    String name;
}
