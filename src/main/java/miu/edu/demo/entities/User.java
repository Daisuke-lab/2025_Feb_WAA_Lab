package miu.edu.demo.entities;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.querydsl.core.annotations.QueryEntity;
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
@QueryEntity
public class User {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    long id;
    String name;
    @JsonIgnore
    @OneToMany(cascade = CascadeType.REMOVE, orphanRemoval = true, mappedBy = "user", fetch = FetchType.LAZY)
    List<Post> posts;

}


