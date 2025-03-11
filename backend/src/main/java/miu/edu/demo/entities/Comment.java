package miu.edu.demo.entities;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.querydsl.core.annotations.QueryEntity;
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
@QueryEntity
public class Comment {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    long id;
    String name;

    @JsonIgnore
    @ManyToOne
    Post post;


}
