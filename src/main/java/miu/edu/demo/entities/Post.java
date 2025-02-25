package miu.edu.demo.entities;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;

@Getter
@Setter
@RequiredArgsConstructor
@AllArgsConstructor
@Entity
public class Post {

    long id;
    String title;
    String content;
    String author;

}
