package miu.edu.demo.repositories;
import miu.edu.demo.entities.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import miu.edu.demo.entities.User;

@Repository
public interface PostRepository extends JpaRepository<Post, Long> {
}
