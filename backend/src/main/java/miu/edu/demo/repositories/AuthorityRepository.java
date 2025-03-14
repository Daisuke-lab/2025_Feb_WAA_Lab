package miu.edu.demo.repositories;
import miu.edu.demo.entities.Authority;
import miu.edu.demo.entities.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.stereotype.Repository;
import miu.edu.demo.entities.Comment;

@Repository
public interface AuthorityRepository extends JpaRepository<Authority, Long> {

}