package miu.edu.demo.entities;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;




@Getter
@Setter
@RequiredArgsConstructor
@AllArgsConstructor
@Entity
@Table
public class Post {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    long id;
    String title;
    String content;
    String author;
    @ManyToOne
    @JoinColumn(name="post_user_id", nullable=false)
    User user;

}
