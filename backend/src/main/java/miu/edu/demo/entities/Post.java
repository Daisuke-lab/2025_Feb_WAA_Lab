package miu.edu.demo.entities;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.querydsl.core.annotations.QueryEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import java.util.List;



@Getter
@Setter
@RequiredArgsConstructor
@AllArgsConstructor
@QueryEntity
@Entity
public class Post {

    @ManyToOne(fetch = FetchType.LAZY)
    User user;

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    long id;
    String title;
    String content;
    String author;


    //@JsonIgnore
    @OneToMany(cascade = CascadeType.REMOVE, orphanRemoval = true, mappedBy = "post", fetch = FetchType.EAGER)
    @Fetch(FetchMode.JOIN)
    List<Comment> comments;

}
