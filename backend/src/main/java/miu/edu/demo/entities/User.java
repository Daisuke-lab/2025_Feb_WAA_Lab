package miu.edu.demo.entities;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.querydsl.core.annotations.QueryEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.util.Collection;
import java.util.List;

import jakarta.persistence.*;
import org.hibernate.annotations.BatchSize;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import org.springframework.lang.Nullable;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

@Getter
@Setter
@RequiredArgsConstructor
@AllArgsConstructor
@QueryEntity
@Table(name = "PostUser")
@Entity
public class User {



//    @OneToOne(mappedBy = "user")
//    Address address;



    @JoinColumn(name = "post_user_id")
    @JsonIgnore
    @Fetch(FetchMode.SELECT)
    @BatchSize(size = 100)
    @OneToMany(cascade=CascadeType.ALL, fetch=FetchType.LAZY)
    List<Post> posts;

//    @Column(nullable = true)
//    float gpa;



    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    long id;
    String name;







}


