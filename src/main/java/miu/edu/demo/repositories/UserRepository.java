package miu.edu.demo.repositories;
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
}
