package miu.edu.demo.entities;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.querydsl.core.annotations.QueryEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import java.util.List;
import java.util.Set;

import jakarta.persistence.*;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

@Getter
@Setter
@RequiredArgsConstructor
@AllArgsConstructor
@QueryEntity
@Entity
@Table(name = "PostUser")
public class User {

    @JoinColumn(name = "post_user_id")
    @JsonIgnore
    @OneToMany(cascade = CascadeType.REMOVE, orphanRemoval = true, fetch = FetchType.LAZY)
    @Fetch(FetchMode.SUBSELECT)
    List<Post> posts;



    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    long id;
    String name;





}


