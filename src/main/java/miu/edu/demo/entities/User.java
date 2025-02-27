package miu.edu.demo.entities;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import java.util.List;
import java.util.Set;

import jakarta.persistence.*;

@Getter
@Setter
@RequiredArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "PostUser")
public class User {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    long id;
    String name;

    @OneToMany(mappedBy="user")
    List<Post> posts;

}


