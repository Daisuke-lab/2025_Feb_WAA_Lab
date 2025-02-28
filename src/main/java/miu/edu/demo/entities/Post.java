package miu.edu.demo.entities;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.querydsl.core.annotations.QueryEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import java.util.List;



@Getter
@Setter
@RequiredArgsConstructor
@AllArgsConstructor
@QueryEntity
@Entity
public class Post {

    //@ManyToOne(fetch = FetchType.LAZY)
    //@JoinColumn(name="user_id", nullable=false)
    //User user;

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    long id;
    String title;
    String content;
    String author;


//    @JsonIgnore
//    @OneToMany(cascade = CascadeType.REMOVE, orphanRemoval = true, mappedBy = "post")
//    List<Comment> comments;

}
