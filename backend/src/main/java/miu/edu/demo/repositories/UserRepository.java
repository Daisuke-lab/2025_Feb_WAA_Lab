package miu.edu.demo.repositories;
import miu.edu.demo.entities.Post;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import miu.edu.demo.entities.User;
import java.util.List;

@Repository
public interface UserRepository  extends JpaRepository<User, Long> ,
        QuerydslPredicateExecutor<User>,
        JpaSpecificationExecutor<User> {

    List<User> findAll(Specification<User> spec);

    @Query(value = "select u1_0.id,u1_0.name from post_user u1_0 left join post p1_0 on u1_0.id=p1_0.post_user_id group by u1_0.id having count(p1_0.id)>= :postCount", nativeQuery = true)
    List<User> findUsersWithPost(@Param("postCount") Integer postCount);


    @Query("SELECT u FROM User u JOIN u.posts p WHERE SIZE(u.posts) > :postCount")
    List<User> findUsersWithMoreThanXPosts(@Param("postCount") int postCount);

    // Worked! "SELECT u FROM User u WHERE SIZE(u.posts) > 1"
    // Worked! "SELECT u FROM User u JOIN u.posts p WHERE SIZE(p) > 1"
    // Worked! "SELECT u FROM User u JOIN u.posts p WHERE SIZE(p.comments) > 1"
    // Worked! "SELECT DISTINCT u FROM User u JOIN u.posts p JOIN p.comments c GROUP BY u.id, p.id HAVING COUNT(c) > 1"
    // Worked! "SELECT DISTINCT u FROM User u JOIN u.posts p JOIN p.comments c GROUP BY u, p HAVING COUNT(c) > 0"
    // Worked! "SELECT u.posts FROM User u WHERE u.id = 1"
    // Doesn't work "SELECT p FROM Post p WHERE p.user.id = 1"
    // Doesn't work "SELECT p FROM Post p WHERE p.post_user_id = 1")
    @Query("SELECT p FROM Post p WHERE p.author LIKE '%test%' ")
    List<Post> findUsersWTest();

    @Query("SELECT p FROM User u JOIN u.posts p WHERE u.id = :id")
    List<Post> findPostsWithUser(@Param("id") Long id);


    @Query("SELECT u FROM User u JOIN u.posts p JOIN p.comments c WHERE c.name = 'test'")
    List<User> findUsersWithMoreThanXPosts2(@Param("postCount") int postCount);
    
   // List<User> findUsersByGpaGreaterThanEqual(@Param("gpa") float gpa);
}
